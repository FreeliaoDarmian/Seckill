package com.example.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRU extends CacheStragtegy {

    ConcurrentHashMap concurrentHashMap;
    ConcurrentLinkedQueue concurrentLinkedQueue;
    JedisUtils jedisUtils;
    ReentrantReadWriteLock reentrantReadWriteLock;



    @Override
    public Object Put(RedisKey key,String value) {
        return null;
    }

    @Override
    public Object Get(RedisKey ket,Class T) {
        return null;
    }


}
