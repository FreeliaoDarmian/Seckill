package com.example.demo.templates.output;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import com.example.demo.templates.metaData.Table;
import com.example.demo.templates.templates.CodeTemplate;

import java.io.File;
import java.io.IOException;

public class FileCodeOutput extends CodeOutput{

    public FileCodeOutput() {
        super(1, "文件生成");
    }

    @Override
    public void out(Table table, String content, CodeTemplate template)  {
        /*Console.log("\n");
        Console.log("-----------------------------");
        Console.log(content);
        Console.log("-----------------------------");*/
        /*new File("").getCanonicalPath()*/
        String fileName = "src\\main\\java\\com\\example\\demo\\entity"+"\\" + table.getClassName() + template.getFileTag() + template.getFileSuffix();
        File file = new File(fileName);
        FileUtil.writeBytes(content.getBytes(),file);
        Console.log("文件位置:" + file.getAbsolutePath());
    }
}
