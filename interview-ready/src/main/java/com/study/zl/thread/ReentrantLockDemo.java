package com.study.zl.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author long
 * @Date 2019/8/11 17:53
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println("-->");
        lock.unlock();
        Condition condition = lock.newCondition();
        condition.await();
        condition.signal();
    }
}
