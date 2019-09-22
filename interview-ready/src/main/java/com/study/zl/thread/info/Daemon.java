package com.study.zl.thread.info;

import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/25 10:13
 */
public class Daemon {

    public static void main(String[] args) {
        Thread t = new Thread(new DaemonRunner(), "DaemonRunner");
        t.setDaemon(true);
        t.start();
    }

    // 守护线程finally里面的代码不一定会执行
    static class DaemonRunner implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("i am daemon!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("DaemonRunner finally code!");
            }
        }
    }

}
