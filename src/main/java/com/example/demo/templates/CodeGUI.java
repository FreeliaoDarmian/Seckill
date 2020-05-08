package com.example.demo.templates;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.example.demo.templates.command.Command;
import com.example.demo.templates.configuration.CodeConfiguration;
import com.example.demo.templates.output.CodeOutput;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class CodeGUI {

    public static int flag=1;

    public CodeConfiguration codeConfiguration;
    public CodeRunner codeRunner;

    public CodeGUI(){
        codeConfiguration=new CodeConfiguration();
        codeRunner=new CodeRunner(codeConfiguration);
    }
    public void excute(){
        Console.log("开始执行任务");
        Console.log("-----");
        Command command=null;
        CodeOutput codeOutput=null;
        Scanner scanner=new Scanner(System.in);
        command=selectCommand(codeConfiguration,scanner);
        codeOutput=selectOutput(codeConfiguration,scanner);
        String tableName=selectTableName(codeOutput,scanner);


        Console.log("开始执行");
        codeRunner.excute(command,codeOutput,tableName);
        Console.log("生成完毕");
        Console.log("是否继续");
        flag=scanner.nextInt();
    }

    private String selectTableName(CodeOutput codeOutput, Scanner scanner) {
        Console.log("请输入需要生成的表名");
        Console.log("-----");
        String tableName=null;
        while(scanner.hasNext()){
            tableName=scanner.next();
            if(StrUtil.isBlank(tableName)){
                Console.error("表名不能为空");
                continue;
            }
            Console.log("输入的表名:{}",tableName);
            break;
        }
        return tableName;
    }

    private CodeOutput selectOutput(CodeConfiguration codeConfiguration,Scanner scanner) {
        Console.log("请输入需要生成的方式");
        Console.log("-----");
        Collection<CodeOutput> codeOutputs=codeConfiguration.getOutputs();
        for(CodeOutput codeOutput:codeOutputs){
            Console.log("{}-{}",codeOutput.getCodeOutputCode(),codeOutput.getCodeOutputName());
        }
        CodeOutput codeOutput=null;
        while(scanner.hasNext()){
            int tar=scanner.nextInt();
            codeOutput=codeConfiguration.getCodeOutput(tar);
            if(codeOutput==null){
                Console.log("命令不存在,请重新输入");
               continue;
            }
            Console.log("你输入的命令:{}",codeOutput.getCodeOutputName());
            break;
        }
        return codeOutput;
    }

    private Command selectCommand(CodeConfiguration codeConfiguration,Scanner scanner) {
        Console.log("请选择要生成的代码");
        Console.log("-----");
        Collection<Command> list= codeConfiguration.getCommands();
        for(Command command:list){
            Console.log("{}-{}",command.getCommandCode(),command.getCommandName());
        }

        Console.log("请输入选择:");
        Command command=null;
        int target=scanner.nextInt();
        command =codeConfiguration.getCommand(target);
        if(command==null){
            Console.log("未找到{}的命令",target);
        }
        return command;
    }

    public static void main(String args[]){
        while (flag==1){
            new CodeGUI().excute();
        }
    }
}
