package com.example.demo.templates.db;

import java.sql.*;
import java.util.Properties;

public class TemplateService {

    private static Connection connection;

    public static Connection getConnection(){
        String driver ="com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://127.0.0.1:3306/shop_test?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
        String username="root";
        String password="darmian1998";

        Properties props = new Properties();
        props.put("remarksReporting","true");//获取数据库的备注信息
        props.put("user",username);
        props.put("password",password);

        //1.获取连接
        try {
            Class.forName(driver);//注册驱动
            connection = DriverManager.getConnection(url,props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConn(Connection conn){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*public static void main(String args[]){
        DatabaseMetaData databaseMetaData;
        ResultSet resultSet;
        try {
             databaseMetaData =connection.getMetaData();
             // 获取所有表 resultSet=databaseMetaData.getCatalogs();
            resultSet=databaseMetaData.getColumns(null, null, "course" ,null);
            while(resultSet.next()){
                System.out.println(resultSet.getString(4));
            }
            //sql  注：pe_user这个是我的数据库表名
            String sql = "select * from student where s_id=?";
            //PreparedStatement
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "2");
            //查询
            ResultSet rs = pstmt.executeQuery();

            //获取结果集元数据
            ResultSetMetaData metaData = rs.getMetaData();

            //获取查询字段个数
            int count = metaData.getColumnCount();

            for(int i =1;i<=count ;i++) {
                //获取列名
                String columnName = metaData.getColumnName(i);
                //获取字段类型 sql类型
                String columnType = metaData.getColumnTypeName(i);
                //获取java类型
                String columnClassName = metaData.getColumnClassName(i);
                System.out.println(columnName+"--"+columnType+"---"+columnClassName);
            }

            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }
    }*/






}
