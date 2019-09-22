package com.study.jedis.service;

import com.alibaba.fastjson.JSON;
import com.study.jedis.queue.RedisDelayingQueue;
import com.study.jedis.util.TaskItem;

import java.util.Random;
import java.util.UUID;

/**
 * @Author long
 * @Date 2019/9/8 17:13
 * 延时队列-生产者线程
 */
public class DelayMessageProducer extends Thread {

    RedisDelayingQueue queue;

    private static final Random random = new Random();


    public DelayMessageProducer(RedisDelayingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10 ; i++) {
            String message = "message:"+ i;
            send(message);
        }
    }

    /**
     * 发送消息
     * @param msg
     */
    private void send(String msg) {
        TaskItem item = buildMessage(msg);
        String message = JSON.toJSONString(item);
        queue.delay(message, item.score);
    }

    /**
     * 创建消息实体
     * @param msg
     * @return
     */
    private TaskItem buildMessage(String msg) {
        TaskItem task = new TaskItem();
        task.id = UUID.randomUUID().toString();
        task.msg = msg;
        long score = random.nextInt(100);
        task.score = score;
        return task;
    }
}
