package com.study.zl.thread.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @Author long
 * @Date 2019/8/25 13:14
 * 线程池
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    /**
     * 初始化线程池
     * @param initialSize
     */
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    /**
     * 释放一个连接
     * @param connection
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 获取线程池
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        // 若是未设置超时时间,当线程池为空时,将线程放入到pool的等待队列中
        if (mills <= 0) {
            while (pool.isEmpty()) {
                pool.wait();
            }
            return pool.removeFirst();
        // 设置了超时等待
        } else {
            long future = System.currentTimeMillis() + mills;
            long waitTime = mills;
            while (pool.isEmpty() && waitTime > 0) {
                pool.wait(waitTime);
                waitTime = future - System.currentTimeMillis();
            }
            Connection result = null;
            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }
            return result;
        }
    }

}
