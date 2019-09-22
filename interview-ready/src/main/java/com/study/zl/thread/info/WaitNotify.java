package com.study.zl.thread.info;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/25 11:07
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }



    static class Wait implements Runnable {
        @Override
        public void run() {
            synchronized (lock) {
                // 条件未满足时,线程进入等待，并释放了lock锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wai@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 当条件满足时,正常执行
                System.out.println(Thread.currentThread() + " flag is false. running@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock) {
                // 获取到锁，然后进行通知，通知时不会释放lock的锁
                // 知道当前的线程释放完成了lock锁之后，wait的线程才能正常返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " +                 new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }
        }
    }

}
