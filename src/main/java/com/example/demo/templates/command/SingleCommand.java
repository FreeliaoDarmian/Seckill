package com.example.demo.templates.command;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.templates.metaData.Table;
import com.example.demo.templates.output.CodeOutput;
import com.example.demo.templates.templates.CodeTemplate;
import com.example.demo.templates.templates.EntiyTemplates;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Map;

public class SingleCommand extends Command {

    public CodeTemplate codeTemplate;


    public SingleCommand(){
        super("生成实体类",1);
    }


    @Override
    public void excute(Table table, SpringTemplateEngine springTemplateEngine, CodeOutput codeOutput) {
            Map map = BeanUtil.beanToMap(table);
            map.put("packageName","entity");
            //this.process(table,map);
            Context context = new Context();
            context.setVariables(map);
            this.codeTemplate=new EntiyTemplates();
            String result = springTemplateEngine.process(codeTemplate.read(),context);
            codeOutput.out(table,result,codeTemplate);
        }
    }
