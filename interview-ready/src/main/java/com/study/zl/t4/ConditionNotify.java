package com.study.zl.t4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author long
 * @Date 2019/8/10 10:18
 */
public class ConditionNotify implements Runnable {

    private Lock lock;
    private Condition condition;

    public ConditionNotify(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("ConditionNotify before");
            condition.signal();
            System.out.println("ConditionNotify after");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
