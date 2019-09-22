package com.gupao.ready;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Author long
 * @Date 2019/7/29 18:03
 * 使用Future实现熔断机制
 */
public class FutureDemo {

    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService service = Executors.newFixedThreadPool(1);

        Future<String> future = service.submit(() -> {
            int time = random.nextInt(200);
            System.out.println("program execute time cost " + time);
            TimeUnit.MILLISECONDS.sleep(time);
            return "hello,world";
        });

        try {
            String result = future.get(100, TimeUnit.MILLISECONDS);
            System.out.println("program execute result " + result);
        } catch (Exception e) {
            System.out.println("execute protected program");
        }

        service.shutdown();
    }
}
