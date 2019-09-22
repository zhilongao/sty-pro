package com.study.zl.pattern.factory.abstr;

/**
 * @Author long
 * @Date 2019/3/2 16:53
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        AbstractMilkFactory factory = new MilkFactory();

        System.out.println(factory.getMenniu());
        System.out.println(factory.getTelunsu());
        System.out.println(factory.getYili());
    }
}
