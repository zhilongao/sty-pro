package com.study.zl.thread.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/25 17:54
 */
public class CountDownLatchTask {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        Thread task1 = new Thread(new ParserTask("任务1", latch));
        Thread task2 = new Thread(new ParserTask("任务2", latch));
        task1.start();
        task2.start();
        //task1.join();
        //task2.join();
        latch.await();
        System.out.println("all parser finished");
    }

}



class ParserTask implements Runnable {

    String taskName;

    CountDownLatch latch;

    ParserTask(String taskName, CountDownLatch latch) {
        this.taskName = taskName;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始执行解析任务-->" + taskName);
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "执行解析任务完成-->" + taskName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }

    }
}