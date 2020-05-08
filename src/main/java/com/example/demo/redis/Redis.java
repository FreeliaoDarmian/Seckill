package com.example.demo.redis;

import com.example.demo.enums.RedisReturnType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Redis {

    /**
     * key的生成策略 默认用:链接
     * 比如 user:1
     * @return
     */
    String filed() default "";

    /**
     * json序列化类型
     * @return
     */
    RedisReturnType redisReturnType();

    /**
     * 过期时间默认 60*60*24
     * @return
     */
    int expireTime() default 86400;

}
