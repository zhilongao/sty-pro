package com.study.zl.safe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Author long
 * @Date 2019/9/28 9:56
 */
public class FlexibleAtomicInteger {

    private static  Unsafe unsafe;
    private static long valueOffset;

    static {
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (Exception e) {
            try {
                Field field = Unsafe.class.getDeclaredField("theUnsafe");
                field.setAccessible(true);
                unsafe = (Unsafe)field.get(null);
            } catch (Exception o) {
                o.printStackTrace();
            }
        }
        try {
            valueOffset = unsafe.objectFieldOffset(FlexibleAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private volatile int value;

    public final int getAndIncrementDouble() {
        return unsafe.getAndAddInt(this, valueOffset, 2);
    }

    public final int get() {
        return this.value;
    }
}
