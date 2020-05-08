package com.example.demo.service;

import com.example.demo.redis.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenServiceImpl /*implements TokenService*/ {

    /*@Autowired
    JedisUtils jedisUtils;

    @Override
    public String createToken() {
        String suffix=this.getString(8);
        StringBuffer stringBuffer=new StringBuffer("token_");
        stringBuffer.append(suffix);
        jedisUtils.setEx(stringBuffer.toString(),stringBuffer.toString(),100000);
        return stringBuffer.toString();
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        String token_name=request.getHeader(TokenService.TOKEN_Name);
        if(token_name.length()<=0 || StringUtils.isEmpty(token_name)){
            token_name=request.getParameter(TokenService.TOKEN_Name);

        }
        return false;
    }
       */

}
