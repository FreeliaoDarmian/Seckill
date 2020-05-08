package com.example.demo.templates.output;

import com.example.demo.templates.metaData.Table;
import com.example.demo.templates.templates.CodeTemplate;

public abstract class CodeOutput {
    protected Integer codeOutputCode;

    protected String codeOutputName;

    public CodeOutput(Integer codeOutputCode,String codeOutputName){
        this.codeOutputCode=codeOutputCode;
        this.codeOutputName=codeOutputName;
    }

    public Integer getCodeOutputCode() {
        return codeOutputCode;
    }

    public String getCodeOutputName() {
        return codeOutputName;
    }

    public abstract void out(Table table, String content, CodeTemplate template);

}
