package com.study.zl.thread.context;

import javax.swing.plaf.synth.SynthTabbedPaneUI;

/**
 * @Author long
 * @Date 2019/8/26 18:51
 */
public class ConcurrencyTest {


    private static final long count = 100000000l;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    /**
     * 该方法是让两个线程来执行a和b的递加或是递减操作，知道两个线程都执行完成，计算执行时间
     * @throws InterruptedException
     */
    public static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.printf("concurrent cost:%s, b:%s\n", time, b);
    }

    /**
     * 该方法是使用一个线程来执行对于a和b的递减和递加操作
     */
    public static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b --;
        }
        long time = System.currentTimeMillis() - start;
        System.out.printf("serial all cost:%s, a:%s, b:%s\n", time, a, b);
    }



}
