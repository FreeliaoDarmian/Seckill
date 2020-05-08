package com.example.demo.utils;

import cn.hutool.core.lang.Console;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ExcutorUtils {

    private static ExecutorService executor;

    public static ExecutorService getExecutor(){
        if(executor==null){
            synchronized (ExcutorUtils.class){
            if(executor==null) {
                executor = Executors.newSingleThreadExecutor();
                return executor;
            }else
                return  executor;
        }
        }else
            return executor;
    }

}
