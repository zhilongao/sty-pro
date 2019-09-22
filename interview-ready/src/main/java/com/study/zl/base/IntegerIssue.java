package com.study.zl.base;

import java.lang.reflect.Field;

/**
 * @Author long
 * @Date 2019/7/24 9:31
 * @Description Integer常见面试题分析
 */
public class IntegerIssue {

    public static void main(String[] args) {
        integerCompare();
        System.out.println("==========================");
        swapInteger();
    }

    /**
     * Integer compare analysis
     */
    public static void integerCompare() {

        Integer m = Integer.valueOf(3);
        System.out.println(m);
        System.out.println("=======");

        Integer i1 = 2;
        Integer i2 = 2;
        Integer i3 = new Integer(3);
        Integer i4 = new Integer(3);
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }

    public static void swapInteger() {
        Integer a = 3;
        Integer b = 5;
        System.out.println("before: a = " + a + "  b = " + b);
        doSwapInteger(a, b);
        System.out.println("after: a = " + a + "  b = " + b);
    }

    public static void doSwapInteger(Integer i1, Integer i2) {
        // method one
        /*
        Integer temp = i1;
        i1 = i2;
        i2 = temp;
        */
        // method two
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            // error method
            Integer temp1 = i1;
            field.set(i1, i2);
            field.set(i2, temp1);

            /*
            int temp2 = i1.intValue();
            field.setInt(i1, i2.intValue());
            field.setInt(i2, temp2);
            */

            /*Integer temp3 = new Integer(i1.intValue());
            field.set(i1, i2.intValue());
            field.set(i2, temp3);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
