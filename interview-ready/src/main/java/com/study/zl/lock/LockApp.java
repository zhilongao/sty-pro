package com.study.zl.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author long
 * @Date 2019/8/3 17:12
 */
public class LockApp {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.unlock();
        Condition condition = lock.newCondition();
        condition.await();
        condition.signal();


        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.put("");
        queue.add("");
        queue.offer("");
        queue.poll();
        queue.remove("");
        ArrayBlockingQueue<String> queue1 = new ArrayBlockingQueue<>(10);
        queue1.put("");
        queue1.add("");
        queue1.offer("");
        queue1.take();
        queue1.poll();
        queue1.remove();

    }
}
