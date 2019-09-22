package com.study.apply;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author long
 * @Date 2019/7/24 14:27
 */
public class App {

    public static void main( String[] args ) throws IOException {
        final CountDownLatch countDownLatch=new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        DistributedLock distributedLock = new DistributedLock();
                        distributedLock.lock(); //获得锁
                    } catch (InterruptedException e) {

                    }
                }
            }).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }

}
