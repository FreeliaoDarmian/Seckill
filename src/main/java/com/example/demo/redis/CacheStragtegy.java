package com.example.demo.redis;

public abstract class CacheStragtegy {

    public abstract Object Put(RedisKey key,String value);

    public abstract Object Get(RedisKey ket,Class T);

}
