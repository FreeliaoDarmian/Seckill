package com.example.demo.utils;

import cn.hutool.core.lang.Console;

import java.util.concurrent.*;

public class QQSignInEvent extends SignInListener {
    @Override
    public boolean signIn() {
        ExecutorService executor=ExcutorUtils.getExecutor();
        executor.execute(()->{
            while(super.count.intValue()>0&&flag!=1){
                super.count.decrementAndGet();
                Console.log("剩余数量{}",super.count.intValue());
                if(super.count.intValue()<=0){
                    executor.shutdown();
                }
            }
        });
        return false;
    }

    @Override
    public boolean support(SignInEvent event) {
        return false;
    }
}
