package com.example.demo.aop;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import com.example.demo.enums.RedisReturnType;
import com.example.demo.redis.JedisUtils;
import com.example.demo.redis.Redis;
import com.example.demo.utils.JSONUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class SeckillAspect {



    @Pointcut("excution(*com.example.demo.rest.SeckillController.getSeckillUrl(..))")
    public void print() {

    }

    @Around("@annotation(com.example.demo.redis.Redis)")
    public Object before(final ProceedingJoinPoint jp) throws Throwable {

        Method method=getMethod(jp);
        Redis cache=method.getAnnotation(Redis.class);
        //根据类名 方法名 参数生成key
        String key=parseKey(cache.filed(),method,jp.getArgs());
        Console.log("生成key{}",key);

        //得到被代理方法上的注解
        RedisReturnType redisReturnType=method.getAnnotation(Redis.class).redisReturnType();
        String returnType=redisReturnType.getType();

        String redisRs=JedisUtils.get(key);
        Object result=null;

        if(StringUtils.isNotEmpty(redisRs)){
            Console.log("命中缓存");
            if(returnType.equals(RedisReturnType.list.getType())){
                result=JSONUtils.json2Map(redisRs);
            }else if(returnType.equals(RedisReturnType.string.getType())){
                result=new ResponseEntity<>(redisRs,HttpStatus.OK);
            }
        }else{
            Console.log("缓存未命中");
            result=jp.proceed(jp.getArgs());
            JedisUtils.setEx(key,JSONUtils.object2json(result),cache.expireTime());
        }
        return result;

    }

    private String parseKey(String filed, Method method, Object[] args) {
        if(StringUtils.isEmpty(filed)){
            return method.getName();
        }

        //_号分隔
        String SpEl=filed.replace("_","+_+");
        //获取被拦截方法的参数列表
        LocalVariableTableParameterNameDiscoverer localVariableTableParameterNameDiscoverer=new LocalVariableTableParameterNameDiscoverer();
        String[] paramters=localVariableTableParameterNameDiscoverer.getParameterNames(method);
        ExpressionParser parser=new SpelExpressionParser();
        StandardEvaluationContext context=new StandardEvaluationContext();
        for(int i=0;i<paramters.length;i++){
            context.setVariable(paramters[i],args[i]);
        }
        return  method.getName()+parser.parseExpression(SpEl).getValue(context,String.class);
    }


    private Method getMethod(ProceedingJoinPoint jp) throws NoSuchMethodException {
        Signature sig =jp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = jp.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        return currentMethod;
    }




}
