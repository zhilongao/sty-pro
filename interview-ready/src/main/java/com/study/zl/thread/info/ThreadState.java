package com.study.zl.thread.info;

import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/25 9:46
 */
public class ThreadState {

    public static void main(String[] args) {
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // 两个Blocked线程，一个获取锁成功，一个获取锁失败
        new Thread(new Blocked(), "Blocked-1").start();
        new Thread(new Blocked(), "Blocked-2").start();
    }

    // 该线程将会不断的休眠
    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            SleepUtils.second(100);
        }
    }

    // 该线程将会在Waiting实例上等待
    static class Waiting implements Runnable{
        @Override
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // 该线程在Blocked上加锁后不会释放该锁
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100);
                }
            }
        }
    }
}

