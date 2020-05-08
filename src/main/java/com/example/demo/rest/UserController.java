package com.example.demo.rest;

import com.example.demo.annotion.isLogin;
import com.example.demo.entity.Student;
import com.example.demo.utils.VotoBeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user/")
public class UserController {

    @RequestMapping(value="/signUp",method = {RequestMethod.POST})
    public ResponseEntity login(){
        return null;
    }

    @RequestMapping(value="/signIn",method = RequestMethod.POST)
    @isLogin(value="成功")
    public Object signIn(Map<String,Object> res) throws InstantiationException, IllegalAccessException {
        return VotoBeanUtils.toBean(res, Student.class);
    }

    @RequestMapping(value="/loginOut",method ={RequestMethod.POST})
    public ResponseEntity loginOut(){
        return null;
    }


}
