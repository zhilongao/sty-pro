package com.study.zl.design;

import java.util.concurrent.CountDownLatch;

/**
 * @Author long
 * @Date 2019/8/8 9:23
 * 订单->验证
 */
public class ValidateOrderProcessor extends AbstractOrderProcessor{

    private OrderProcessor orderProcessor;


    public ValidateOrderProcessor(CountDownLatch latch) {
        super(latch);
    }

    public ValidateOrderProcessor(OrderProcessor orderProcessor, CountDownLatch latch) {
        super(latch);
        this.orderProcessor = orderProcessor;
    }

    /**
     * 具体处理orderRequest的逻辑
     * @param orderRequest
     */
    @Override
    protected void doProcessor(OrderRequest orderRequest) {
        System.out.println(orderRequest + "->ValidateOrderProcessor deal orderRequest");
        if (orderRequest == null) {
            System.out.println(orderRequest + "->订单不能为空");
            return;
        }
        if (orderRequest.getOrderNo() == null || orderRequest.getOrderName().equals("") || orderRequest.getOrderName() == null) {
            System.out.println(orderRequest + "->orderNo或orderName不能为空");
            return;
        }
        if (this.orderProcessor != null) {
            this.orderProcessor.process(orderRequest);
        }
    }
}
