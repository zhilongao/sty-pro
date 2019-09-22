package com.study.zl.jvm;

/**
 * @Author long
 * @Date 2019/8/16 14:26
 */
public class StringIntern {


    public static void main(String[] args) {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("jav").append("a").toString();
        System.out.println(str2.intern() == str2);

        ThreadLocal local = new ThreadLocal();
    }
}
