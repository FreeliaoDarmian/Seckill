package com.example.demo.templates.command;

import com.example.demo.templates.metaData.Table;
import com.example.demo.templates.output.CodeOutput;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 * 命令类
 */
public abstract class Command {
    /**
     * 命令名称
     */
    protected String commandName;


    /**
     * GUI时使用的code
     */
    protected int commandCode;


    public int getCommandCode() {
        return commandCode;
    }

    public String getCommandName() {
        return commandName;
    }

    public Command(String commandName,int commandCode){
        this.commandCode=commandCode;
        this.commandName=commandName;
    }

    public abstract void excute(Table table, SpringTemplateEngine springTemplateEngine, CodeOutput codeOutput);




}
