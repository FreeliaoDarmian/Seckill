package com.example.demo.enums;



public enum RedisReturnType {
    list("List"),
    string("String"),
    map("map");
    private String type;
    private RedisReturnType(String type){
        this.type=type;
    }

    public String getType() {
        return type;
    }
}
