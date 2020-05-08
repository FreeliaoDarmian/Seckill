package com.example.demo.utils;

import cn.hutool.core.lang.Console;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class VotoBeanUtils {


    public static Object toBean(Map<String,Object> res,Class<?> clazz) throws IllegalAccessException, InstantiationException {
        Object obj=clazz.newInstance();
        if(res!=null && res.size()>0){
            for(Map.Entry<String,Object> entry:res.entrySet()){
                String propertyName=entry.getKey();
                Object value=entry.getValue();
                String setMethodName = "set"
                        + propertyName.substring(0, 1)
                        + propertyName.substring(1);
                Console.log("方法名{}",setMethodName);
                Field field = getClassField(clazz, propertyName);
                if(field==null)
                    continue;
                Class<?> fieldTypeClass = field.getType();
                value = convertValType(value, fieldTypeClass);
                try{
                    clazz.getMethod(setMethodName, field.getType()).invoke(obj, value);
                }catch(NoSuchMethodException e){
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            }
        return obj;
    }

    private static Object convertValType(Object value, Class<?> fieldTypeClass) {
        Object retVal = null;
        if(String.class.getName().equals(fieldTypeClass.getName())||String.class.getName().equals(fieldTypeClass.getName())){
            retVal=String.valueOf(value.toString());
        } else if(Long.class.getName().equals(fieldTypeClass.getName())
                || long.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Long.parseLong(value.toString());
        } else if(Integer.class.getName().equals(fieldTypeClass.getName())
                || int.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Integer.parseInt(value.toString());
        } else if(Float.class.getName().equals(fieldTypeClass.getName())
                || float.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Float.parseFloat(value.toString());
        } else if(Double.class.getName().equals(fieldTypeClass.getName())
                || double.class.getName().equals(fieldTypeClass.getName())) {
            retVal = Double.parseDouble(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }



    private static Field getClassField(Class<?> clazz, String fieldName) {
        if( Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field []declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }

        Class<?> superClass = clazz.getSuperclass();
        if(superClass != null) {// 简单的递归一下
            return getClassField(superClass, fieldName);
        }
        return null;
    }



}
