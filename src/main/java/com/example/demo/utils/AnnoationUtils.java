package com.example.demo.utils;

import cn.hutool.core.lang.Console;
import com.example.demo.annotion.isLogin;

import java.lang.reflect.Method;

public class AnnoationUtils {


    public static boolean getAnnoation(Method method){
        if(method.isAnnotationPresent(isLogin.class)){
            isLogin login=method.getAnnotation(isLogin.class);
            for(String val:login.value()){
                Console.log(val);
                return true;
            }
        }
        return false;
    }

}
