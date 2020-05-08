package com.example.demo.templates.metaData;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Console;
import com.example.demo.templates.configuration.TypeMapping;
import com.example.demo.templates.db.TemplateService;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MetaData {


    public static Table getTableInfo(String tableName, TypeMapping typeMapping) {
        String result = "";
        Connection conn = TemplateService.getConnection();
        Table table = new Table();
        try {
            table.setCode(tableName);

            // 表元数据
            Statement stmt = conn.createStatement();
            String sql = "select table_comment from information_schema.tables where table_name = '" + tableName + "'";
            ResultSet tableResultSet = stmt.executeQuery(sql);
            while (tableResultSet.next()) {
                table.setComment(tableResultSet.getString("table_comment"));
                break;
            }

            // 字段元数据
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet resultSet = metaData.getColumns(null, null, table.getCode(), "%");
            List<TableColumn> tableColumnList = CollectionUtil.newArrayList();
            while (resultSet.next()) {
                TableColumn tableColumn = new TableColumn();
                tableColumn.setCode(resultSet.getString("column_name").toLowerCase());
                tableColumn.setType(typeMapping.getJavaType(resultSet.getString("type_name")));
                tableColumn.setComment(resultSet.getString("remarks"));
                tableColumnList.add(tableColumn);
            }
            tableColumnList=tableColumnList.stream().filter(distinctByKey(b ->b.getCode())).collect(Collectors.toList());
            table.setColumnList(tableColumnList);

            tableResultSet.close();
            stmt.close();
            resultSet.close();
        } catch (SQLException e) {
        } finally {
            // 关闭连接
            TemplateService.closeConn(conn);
        }

        return table;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}