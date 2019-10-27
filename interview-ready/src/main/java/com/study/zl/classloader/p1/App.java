package com.study.zl.classloader.p1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author long
 * @Date 2019/10/13 10:24
 */
public class App {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 双亲委派机制
        // 启动类加载器(BootStrap ClassLoader)->
                // 负责加载虚拟即得核心类库，如：java.lang.*等
                // 系统属性 sun.boot.class.path 所指定的目录中加载类库
                // 依赖底层操作系统 c++实现
        // 扩展类加载器(Extension ClassLoader)->
                // 负责记载 jre\lib\ext目录下的类库
                // 系统属性 java.ext.dirs 所指定的目录中加载类库
                // 纯java实现，是 ClassLoader的子类
        // 系统类加载器(System ClassLoader)
                // 从环境变量 classpath 或者系统属性 java.class.path 指定的目录中记载类
                // 纯java实现，是ClassLoader的子类


        // 系统类加载---()->

        SelfClassLoader selfClassLoader = new SelfClassLoader("D:\\jar");
        Class<?> cls = selfClassLoader.loadClass("com.study.zl.classloader.Test");
        if (cls != null) {
            // 创建一个cls类型的对象
            Object o = cls.newInstance();
            // 获取该对象的say方法
            Method say = cls.getMethod("say", null);
            // 调用该类型的say方法
            say.invoke(o, null);
            // 查看 cls 的类加载器
            System.out.println(cls.getClassLoader().toString());
        }
    }
}
