package com.example.demo.enums;

public enum  Constant {
    Phone("pho",1),Email("email",2),QQ("qq",3);
    final String name;
    final int code;
    Constant(String name ,int code){
     this.code=code;
     this.name=name;
    }

}
