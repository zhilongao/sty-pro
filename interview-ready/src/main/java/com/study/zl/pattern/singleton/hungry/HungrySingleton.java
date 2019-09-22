package com.study.zl.pattern.singleton.hungry;

/**
 * @Author long
 * @Date 2019/3/2 17:58
 * 饿汉式
 * 类加载的时候就立即初始化,并且创建单例对象
 *  优点:没有添加任何的锁,执行效率比较高,用户体验比懒汉式更好
 *  缺点:类加载的时候就初始化,用不用都占有空间,一定程度上浪费了内存
 */
public class HungrySingleton {

    private static final HungrySingleton INSTANCE = new HungrySingleton();

    private HungrySingleton(){

    }
    public static HungrySingleton getInstance(){
        return INSTANCE;
    }
}
