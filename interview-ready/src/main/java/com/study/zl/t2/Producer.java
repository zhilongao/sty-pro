package com.study.zl.t2;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author long
 * @Date 2019/8/8 15:50
 */
public class Producer implements Runnable {

    LinkedBlockingQueue<String> queue;
    AtomicInteger integer = new AtomicInteger(0);

    public Producer(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

    }

    public void produce() {
        synchronized (queue) {
            if (queue.isEmpty()) {
                queue.add("-->消息" + integer.getAndIncrement());
                notify();
            }
        }
    }
}
