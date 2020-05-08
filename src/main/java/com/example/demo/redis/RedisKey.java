package com.example.demo.redis;

public abstract class RedisKey {
    private String prefix;
    private String name;
    private int time;
    RedisKey(String prefix,String name,int time){
        this.name=name;
        this.prefix=prefix;
        this.time=time;
    }


}
