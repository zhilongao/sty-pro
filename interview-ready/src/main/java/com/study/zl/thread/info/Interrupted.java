package com.study.zl.thread.info;

import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/25 10:27
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        sleepThread.start();

        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);
        busyThread.start();


        // 主线程休眠5分钟
        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread isInterupted:" + sleepThread.isInterrupted());
        System.out.println("BusyThread isInterupdate:" + busyThread.isInterrupted());

        SleepUtils.second(2);

    }


    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {

            }
        }
    }


}
