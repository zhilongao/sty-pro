package com.study.jedis.service;

import com.study.jedis.queue.RedisQueue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/9/8 15:48
 * 消息消费者
 */
public class MessageConsumer extends Thread {
    private RedisQueue queue;
    public MessageConsumer(RedisQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            receive();
        }
    }

    private void receive() {
        Object object = queue.poll(true);
        if (object != null) {
            System.out.println(object);
        }
    }
}





