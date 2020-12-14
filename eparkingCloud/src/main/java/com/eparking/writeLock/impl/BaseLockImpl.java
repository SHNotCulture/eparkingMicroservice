package com.eparking.writeLock.impl;

import com.eparking.util.RedisUtil;
import com.eparking.util.SpringUtils;
import com.eparking.writeLock.BaseLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BaseLockImpl<T> implements BaseLock<T> {
    private final static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final static Lock r = rwl.readLock();
    private final static Lock w = rwl.writeLock();
    @Autowired
    private static RedisUtil redisUtil= SpringUtils.getBean(RedisUtil.class);

    private Class<T> entityClass;

    private String entityClassName;

    public  BaseLockImpl(){
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        entityClass = (Class) params[0];//得到传入类型
        entityClassName=entityClass.getSimpleName()+"Lock";//得到传入类名并组装为锁名
       /* try {
            System.out.println(entityClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
    }
    public  boolean containsKey(String key){
        r.lock();
        try {
            Map<String, T> carFeeRspList = new HashMap<String, T>();
            //判断redis服务中是否含有CarFeeRspLock
            if(redisUtil.hasKey(entityClassName)){
                carFeeRspList=(Map<String, T>)redisUtil.get(entityClassName);//得到redis的值
            }
            return carFeeRspList.containsKey(key); }
        finally { r.unlock(); }
    }
    public Collection<T> allValues() {
        r.lock();
        try {
            Map<String, T> carFeeRspList = new HashMap<String, T>();
            //判断redis服务中是否含有Lock
            if(redisUtil.hasKey(entityClassName)){
                carFeeRspList=(Map<String, T>)redisUtil.get(entityClassName);//得到redis的值
            }
            return carFeeRspList.values();}
        finally { r.unlock(); }
    }
    public <T> T get(String key) {
        r.lock();
        try {
            Map<String, T>  List = new HashMap<String, T>();
            //判断redis服务中是否含有Lock
            if(redisUtil.hasKey(entityClassName)){
                List=(Map<String, T>)redisUtil.get(entityClassName);//得到redis的值
            }

            return List.get(key); }
        finally { r.unlock(); }
    }
    public <T> T put(String key, T value) {
        w.lock();
        try {
            Map<String, T> List = new HashMap<String, T>();
            //判断redis服务中是否含有Lock
            if(redisUtil.hasKey(entityClassName)){
                List=(Map<String, T>)redisUtil.get(entityClassName);//得到redis的值
            }
            //判断数据中是否已经包含这个数据
            if(!List.containsKey(key)){
                List.put(key, value);
            }
            redisUtil.set(entityClassName,List);//存储至redis
            return value; }
        finally { w.unlock(); }
    }

    public void remove(String key){
        w.lock();
        try {
            Map<String, T> List = new HashMap<String, T>();
            //判断redis服务中是否含有Lock
            if(redisUtil.hasKey(entityClassName)){
                List=(Map<String, T>)redisUtil.get(entityClassName);//得到redis的值
            }
            List.remove(key);
            redisUtil.set(entityClassName,List);//存储至redis
        }
        finally { w.unlock(); }
    }

    public void clear() {
        w.lock();
        try {
            Map<String, T> List = new HashMap<String, T>();
            //判断redis服务中是否含有Lock
            if(redisUtil.hasKey(entityClassName)){
                List=(Map<String, T>)redisUtil.get(entityClassName);//得到redis的值
            }
            List.clear();
            redisUtil.set(entityClassName,List);//存储至redis
        }
        finally { w.unlock(); }
    }
}
