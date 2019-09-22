package com.study.zl.pattern.singleton.lazy;

/**
 * @Author long
 * @Date 2019/3/9 22:41
 */
public class LazyComplexSingleton {
    private static LazyComplexSingleton singleton;

    private LazyComplexSingleton(){
        // 防止反射攻击
        if (singleton != null) {
            throw new RuntimeException("instance has create");
        }
    }

    public static synchronized LazyComplexSingleton getInstance(){
        if(singleton == null){
            singleton = new LazyComplexSingleton();
        }
        return singleton;
    }

    // 解决序列化和反序列化造成的对象创建不一致
    private Object readResolve(){
        return getInstance();
    }
}
