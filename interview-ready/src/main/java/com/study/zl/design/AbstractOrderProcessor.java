package com.study.zl.design;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Author long
 * @Date 2019/8/8 11:13
 */
public abstract class AbstractOrderProcessor implements OrderProcessor, Runnable {


    private volatile boolean shutDown = false;
    private CountDownLatch latch;
    private LinkedBlockingQueue<OrderRequest> queue = new LinkedBlockingQueue<>();


    public AbstractOrderProcessor(CountDownLatch latch) {
        this.latch = latch;
    }


    public void shutDown() {
        shutDown = true;
    }

    public boolean isShutDown() {
        return shutDown;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public LinkedBlockingQueue<OrderRequest> getQueue() {
        return queue;
    }

    /**
     * 启动线程的方法
     */
    @Override
    public void run() {
        while(!isShutDown()) {
            dealProcess();
        }
    }

    /**
     * 由上一个处理器调用
     * @param orderRequest
     */
    @Override
    public void process(OrderRequest orderRequest) {
        getQueue().add(orderRequest);
    }

    /**
     * 处理请求的方法，由run方法调用
     */
    public final void dealProcess() {
        try {
            OrderRequest orderRequest = getQueue().take();
            doProcessor(orderRequest);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            getLatch().countDown();
        }
    }

    /**
     * 业务方法，由具体子类实现
     * @param orderRequest
     */
    protected void doProcessor(OrderRequest orderRequest) {

    }
}
