package com.study.zl.t4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author long
 * @Date 2019/8/10 10:15
 */
public class ConditionWait implements Runnable{

    private Lock lock;
    private Condition condition;

    public ConditionWait(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            System.out.println("ConditionWait before");
            condition.await();
            System.out.println("ConditionWait after");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
