package com.study.zl.t3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author long
 * @Date 2019/8/8 22:54
 */
public class TestApp {

    public static void main(String[] args) {


        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();

        Condition condition = lock.newCondition();


        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("do something loop");
                }
            });
        }
        service.shutdown();
        service.shutdownNow();

        SelfThreadPool pool = new SelfThreadPool(3, 3);
        for (int i = 0; i < 10; i++) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("currentThread->" + Thread.currentThread().getName() + "----hello,world");
                }
            });
        }

        Thread t1 = new Thread();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
