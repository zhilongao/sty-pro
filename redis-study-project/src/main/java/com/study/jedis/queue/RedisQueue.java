package com.study.jedis.queue;

import com.study.jedis.util.MessageConvert;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Author long
 * @Date 2019/9/8 14:34
 * 分布式消息队列-list实现
 */
public class RedisQueue {

    private Jedis jedis;
    private String queueKey;


    public RedisQueue(Jedis jedis, String queueKey, MessageConvert messageConvert) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    public void push(String message) {
        System.out.println("发送消息:" + message);
       this.jedis.lpush(queueKey, message);

    }

    public String poll(boolean isRetry) {
        String message = jedis.rpop(queueKey);
        if (message == null && isRetry) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            message = jedis.rpop(queueKey);
        }
        return message;
    }
}







