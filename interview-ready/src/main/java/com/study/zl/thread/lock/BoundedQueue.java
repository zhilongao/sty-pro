package com.study.zl.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author long
 * @Date 2019/8/25 17:16
 */
public class BoundedQueue<T> {

    private Object[] items;
    private int addIndex;
    private int removeIndex;
    private int count;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public void add(T t) throws InterruptedException {
        lock.lock();
        try {
            // 容器已满,等待
            while (count == items.length) {
                notFull.await();
            }
            items[addIndex] = t;
            if (++ addIndex == items.length) {
                addIndex = 0;
            }
            count ++;
            // 唤醒消费等待队列上的线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T remove() throws InterruptedException {
        lock.lock();
        try {
            // 若是容器为空
            while (count == 0) {
                notEmpty.await();
            }
            Object item = items[removeIndex];
            if (++ removeIndex == items.length) {
                removeIndex = 0;
            }
            count --;
            // 唤醒生产者线程
            notFull.signal();
            return (T)item;
        } finally {
            lock.unlock();
        }
    }



}
