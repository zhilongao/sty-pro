package com.study.jedis.service;

import com.study.jedis.queue.RedisQueue;
import com.study.jedis.util.MessageConvert;
import com.study.jedis.util.TaskItem;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/9/8 15:47
 * 消息生产者
 */
public class MessageProducer extends Thread {

    // 队列
    private RedisQueue queue;
    // 消息转换器
    private static final MessageConvert<TaskItem> convert = new MessageConvert<>();

    public MessageProducer(RedisQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            send("message:" + i);
            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 创建并发送消息
    private void send(String message) {
        // 创建一条消息
        TaskItem item = new TaskItem();
        item.id = UUID.randomUUID().toString();
        item.msg = message;
        // 转换为字符串发送
        String sendMess = convert.messageToString(item);
        queue.push(sendMess);
    }
}