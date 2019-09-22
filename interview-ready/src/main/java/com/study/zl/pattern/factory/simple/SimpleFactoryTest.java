package com.study.zl.pattern.factory.simple;


/**
 * @Author long
 * @Date 2019/3/2 16:15
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {

        SimpleFactory factory = new SimpleFactory();

        System.out.println(factory.getMilk("蒙牛"));
        System.out.println(factory.getMilk("伊利"));
        System.out.println(factory.getMilk("特仑苏"));
    }
}
