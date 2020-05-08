package com.example.demo.utils;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class SignInListener<T extends SignInEvent> {
    //时间源
    T event;
    //重试次数
    AtomicInteger count=new AtomicInteger(10);
    //事件处理
    public abstract boolean signIn();
    //停止标志
    int flag=0;

    public abstract boolean support(SignInEvent event);
}
