package com.study.zl.pattern.singleton.lazy;

/**
 * @Author long
 * @Date 2019/3/2 19:48
 * 懒汉式 非线程安全,没有加锁
 * 默认不实例化,使用的时候才会去实例化
 */
public class LazySampleSingleton {

    private static LazySampleSingleton lazy = null;

    private LazySampleSingleton(){

    }

    public static synchronized LazySampleSingleton getInstance(){
        if (lazy == null){
            lazy = new LazySampleSingleton();
        }
        return lazy;
    }
}
