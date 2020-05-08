package com.example.demo.templates.configuration;

import com.example.demo.templates.output.CodeOutput;
import com.example.demo.templates.output.FileCodeOutput;

import java.rmi.registry.Registry;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CodeOutputRegister {

    Map<Integer,CodeOutput> list=new LinkedHashMap<>();

    public CodeOutputRegister(){
        register(new FileCodeOutput());
    }


    public Collection<CodeOutput> getOutputs() {
        return list.values();
    }

    public void register(CodeOutput codeOutput){
        list.put(codeOutput.getCodeOutputCode(),codeOutput);
    }

    public CodeOutput getOutput(Integer target){
        return list.get(target);
    }

}
