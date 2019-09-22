package com.study.zl.design;

import java.util.concurrent.CountDownLatch;

/**
 * @Author long
 * @Date 2019/8/8 9:45
 */
public class LogOrderProcessor extends AbstractOrderProcessor{

    private OrderProcessor orderProcessor;

    public LogOrderProcessor(CountDownLatch latch) {
        super(latch);
    }

    public LogOrderProcessor(OrderProcessor orderProcessor, CountDownLatch latch) {
        super(latch);
        this.orderProcessor = orderProcessor;
    }

    /**
     * 具体处理OrderRequest的逻辑
     * @param orderRequest
     */
    @Override
    protected void doProcessor(OrderRequest orderRequest) {
        System.out.println(orderRequest + "->LogOrderProcessor deal orderRequest");
        if (orderProcessor != null) {
            orderProcessor.process(orderRequest);
        } else {
            System.out.println(orderRequest + "->处理完成\n");
        }
    }
}
