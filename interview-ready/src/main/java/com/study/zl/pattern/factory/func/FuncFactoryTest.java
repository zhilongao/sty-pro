package com.study.zl.pattern.factory.func;

/**
 * @Author long
 * @Date 2019/3/2 16:48
 */
public class FuncFactoryTest {
    public static void main(String[] args) {
        FuncMilkFactory yiliFactory = new YiliFactory();
        FuncMilkFactory telunsuFactory = new TelunsuFactory();
        FuncMilkFactory menniuFactory = new MenniuFactory();

        System.out.println(yiliFactory.getMilk());
        System.out.println(telunsuFactory.getMilk());
        System.out.println(menniuFactory.getMilk());
    }
}
