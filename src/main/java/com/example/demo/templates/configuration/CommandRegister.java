package com.example.demo.templates.configuration;

import com.example.demo.templates.command.Command;
import com.example.demo.templates.command.SingleCommand;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommandRegister {

    Map<Integer, Command> list=new LinkedHashMap<>();

    public CommandRegister(){
        register(new SingleCommand());
    }

    public void register(Command command){list.put(command.getCommandCode(),command);}

    public Command getCommand(Integer key){
        return list.get(key);
    }


    public Collection<Command> getCommands(){
        return list.values();
    }




}
