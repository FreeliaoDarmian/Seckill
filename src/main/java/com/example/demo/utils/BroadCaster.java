package com.example.demo.utils;

import cn.hutool.core.lang.Console;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Component
public class BroadCaster {

    private List<SignInListener> listeners=new Vector<>();

    public BroadCaster(){
        registerListner(new EmailSignInListener());
    }
    public void registerListner(SignInListener signInListener){
        listeners.add(signInListener);
    }

    public void broadCaster(SignInEvent event){
        for(SignInListener signInListener:listeners){
            if(signInListener.support(event)){
                Thread t=new Thread(()->{
                    Thread inner=new Thread(()->{
                        signInListener.signIn();
                    });
                    inner.setDaemon(true);
                    /*try{
                    t.join();
                    }catch (InterruptedException e){
                        Console.log("发送消息失败");
                        return;
                    }*/
                });
            }

        }
    }


    public void stop(SignInListener signInListener){

    }








}
