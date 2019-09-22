package com.study.zl.thread.info;

/**
 * @Author long
 * @Date 2019/8/25 10:53
 */
public class MySynchronized {

    public static void main(String[] args) {
        synchronized (MySynchronized.class) {

        }
        m();
    }

    public static synchronized void m() {

    }
}
