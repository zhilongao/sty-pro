package com.study.zl.base;

/**
 * @Author long
 * @Date 2019/7/25 20:09
 *
 * 关于类加载器
 */
public class ClassLoaderIssue {
    public static void main(String[] args) {
        SingletonDemo.getInstance();
        System.out.println("count1=" + SingletonDemo.count1);
        System.out.println("count2=" + SingletonDemo.count2);
    }
}

class SingletonDemo {
    private static SingletonDemo instance = new SingletonDemo();
    public static int count1;
    public static int count2 = 0;

    private SingletonDemo() {
        count1++;
        count2++;
    }

    public static SingletonDemo getInstance() {
        return instance;
    }
}
