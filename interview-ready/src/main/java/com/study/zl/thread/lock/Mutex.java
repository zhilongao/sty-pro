package com.study.zl.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author long
 * @Date 2019/8/25 14:51
 */
public class Mutex implements Lock {

    static ReentrantLock lock = new ReentrantLock();


    // 静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {


        // 是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            lock.lock();
            return getState() == 1;
        }

        // 当状态为0的时候获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // 释放锁
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            } else {
                setExclusiveOwnerThread(null);
                setState(0);
                return true;
            }
        }
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    // 将需要的操作代理到sync上面即可
    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }




    @Override
    public void lockInterruptibly() throws InterruptedException {

    }



    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
