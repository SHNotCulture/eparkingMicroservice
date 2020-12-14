package com.common.util;

import com.common.annotation.ChinaProperty;

import java.lang.reflect.Field;

/**
 * @Author jin
 * @Data 2018/12/20 15:13
 **/
public class ChinaPropertyUtil {
    public static String getChinaProperty(Class<?> clazz,String property){
        Field[] fields = clazz.getDeclaredFields();
        String chinaName="";
        for (Field field :fields){
            if (field.getName().equals(property)) {
                if (field.isAnnotationPresent(ChinaProperty.class)) {
                    ChinaProperty chinaProperty = field.getAnnotation(ChinaProperty.class);
                    chinaName=chinaProperty.chinaName();
                }
            }
        }
        return chinaName;
    }
}
