package com.study.zl.design;

import java.util.concurrent.CountDownLatch;

/**
 * @Author long
 * @Date 2019/8/8 9:33
 */
public class OrderApp {

    public static void main(String[] args) {

        int requestNum = 10;
        CountDownLatch logLatch = new CountDownLatch(requestNum);
        CountDownLatch saveLatch = new CountDownLatch(requestNum);
        CountDownLatch validateLatch = new CountDownLatch(requestNum);

        // 任务
        LogOrderProcessor logOrderProcessor = new LogOrderProcessor(logLatch);
        SaveOrderProcessor saveOrderProcessor = new SaveOrderProcessor(logOrderProcessor, saveLatch);
        ValidateOrderProcessor validateOrderProcessor = new ValidateOrderProcessor(saveOrderProcessor, validateLatch);

        // 线程
        Thread validateThread = new Thread(validateOrderProcessor);
        Thread saveThread = new Thread(saveOrderProcessor);
        Thread logThread = new Thread(logOrderProcessor);

        // 启动三个线程->分别对接收到的订单请求做验证 保存 日志处理
        validateThread.start();
        saveThread.start();
        logThread.start();
        for (int i = 0; i < requestNum; i++) {
            OrderRequest orderRequest = new OrderRequest(i + "", "订单请求"+ i);
            validateOrderProcessor.process(orderRequest);
        }
        try {
            validateLatch.await();
            validateOrderProcessor.shutDown();
            validateThread.interrupt();

            saveThread.interrupt();
            saveLatch.await();
            saveOrderProcessor.shutDown();

            logLatch.await();
            logOrderProcessor.shutDown();
            logThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
