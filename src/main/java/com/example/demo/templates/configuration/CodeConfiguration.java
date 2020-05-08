package com.example.demo.templates.configuration;

import com.example.demo.templates.command.Command;
import com.example.demo.templates.output.CodeOutput;

import java.util.Collection;

public class CodeConfiguration {


      public CommandRegister commandRegister;
      public CodeOutputRegister codeOutputRegister;
      public TypeMapping typeMapping;

      public CodeConfiguration(){
          commandRegister=new CommandRegister();
          codeOutputRegister=new CodeOutputRegister();
          typeMapping=new TypeMapping();
      }

      public Collection<Command> getCommands(){
          return commandRegister.getCommands();
      }


    public Command getCommand(int target) {
          return commandRegister.getCommand(target);
    }

    public Collection<CodeOutput> getOutputs() {
          return codeOutputRegister.getOutputs();
    }
    public CodeOutput getCodeOutput(int target){
          return codeOutputRegister.getOutput(target);
    }

}
