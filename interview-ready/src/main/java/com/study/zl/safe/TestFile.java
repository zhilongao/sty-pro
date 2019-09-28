package com.study.zl.safe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author long
 * @Date 2019/9/18 17:37
 */
public class TestFile {
    // private static volatile  int a = 0;
    public static void main(String[] args) throws InterruptedException {

        LockSupport.park();
        LockSupport.unpark(new Thread());

        // 通过反射获取Unsafe实例
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);

        // 非常规对象实例化
        People p1 = (People)getInstance(People.class);
        People p2 = (People)getInstance(People.class);
        System.out.println(p1);
        System.out.println(p2);
    }
    // 获取Unsafe实例
    private static Unsafe getUnsafe() {
        Unsafe unsafe = null;
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unsafe;
    }
    // 非常规对象实例化
    public static Object getInstance (Class clazz) {
        Unsafe unsafe = getUnsafe();
        Object p = null;
        try {
            p =  unsafe.allocateInstance(clazz);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return p;
    }
}

class People {
    String name;
    int age;

    public People(){
        this.name = "default";
        this.age = 18;
    }
    public People(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + ":" + this.age;
    }
}
