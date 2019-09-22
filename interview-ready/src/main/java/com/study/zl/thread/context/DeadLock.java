package com.study.zl.thread.context;

import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/26 19:26
 */
public class DeadLock {

    private static final String A = "A";
    private static final String B = "B";

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {

                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (A) {

                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
