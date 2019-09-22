package com.study.zl.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author long
 * @Date 2019/3/2 21:26
 * 注册式单例模式
 * spring中的做法,就是使用这种注册式单例去实现的
 * 看到枚举式单例设计模式啦
 * 当前存在线程安全问题
 */
public class BeanFactorySingleton {

    private BeanFactorySingleton(){}

    private static Map<String, Object> ioc = new ConcurrentHashMap<>();

    public static Object getInstance(String className) {
        synchronized (ioc){
            if (!ioc.containsKey(className)) {
                try{
                    ioc.put(className, Class.forName(className).newInstance());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return ioc.get(className);
        }
    }
}
