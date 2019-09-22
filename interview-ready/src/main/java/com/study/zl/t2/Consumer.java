package com.study.zl.t2;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author long
 * @Date 2019/8/8 15:51
 */
public class Consumer implements Runnable {

    LinkedBlockingQueue<String> queue;

    public Consumer(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue) {
            if (queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("消费消息->" + queue.poll());
            }
        }
    }
}
