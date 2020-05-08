package com.example.demo.interceptor;

import com.example.demo.rest.UserController;
import com.example.demo.service.TokenService;
import com.example.demo.utils.AnnoationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;


public class LoginIntecptor implements HandlerInterceptor {

    @Autowired
    public TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer pathName=request.getRequestURL();
        String name=pathName.toString().split("/")[0].substring(0,1).toUpperCase()+pathName.toString().split("/")[0].substring(1)+"Controller";
        Class c=Class.forName("com.example.demo.rest"+"."+name);
        Method method=c.getMethod("signIn", Map.class);
        while(AnnoationUtils.getAnnoation(method)){
            checkToken(request);
        }
        return false;
    }

    private void checkToken(HttpServletRequest request){

    }


}
