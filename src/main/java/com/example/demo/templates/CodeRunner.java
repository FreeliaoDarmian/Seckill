package com.example.demo.templates;

import cn.hutool.core.lang.Console;
import com.example.demo.templates.command.Command;
import com.example.demo.templates.configuration.CodeConfiguration;
import com.example.demo.templates.metaData.MetaData;
import com.example.demo.templates.metaData.Table;
import com.example.demo.templates.metaData.TableColumn;
import com.example.demo.templates.output.CodeOutput;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.dialect.SpringStandardDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

public class CodeRunner {

    public CodeConfiguration codeConfiguration;

    public SpringTemplateEngine springTemplateEngine;

    public CodeRunner(CodeConfiguration codeConfiguration){
        this.codeConfiguration=codeConfiguration;
        springTemplateEngine=new SpringTemplateEngine();
        this.inite();
    }

    private void inite() {
        springTemplateEngine = new SpringTemplateEngine();
        IDialect dialect = new SpringStandardDialect();
        springTemplateEngine.setDialect(dialect);
        // 文本解析器
        StringTemplateResolver resolverText = new StringTemplateResolver();
        resolverText.setCacheable(true);
        resolverText.setTemplateMode(TemplateMode.TEXT);
        // 添加解析器
        springTemplateEngine.addTemplateResolver(resolverText);
    }


    public void excute(Command command, CodeOutput codeOutput, String tableName) {
        Table table= MetaData.getTableInfo(tableName,codeConfiguration.typeMapping);
        for(TableColumn tableColumn:table.getColumnList()){
                Console.log(tableColumn.getAttrName());
        }
        command.excute(table,springTemplateEngine,codeOutput);
    }
}
