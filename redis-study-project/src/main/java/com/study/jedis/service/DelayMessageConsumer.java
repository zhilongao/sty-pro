package com.study.jedis.service;

import com.study.jedis.handle.DelayMessageHandle;
import com.study.jedis.handle.MessageHandle;
import com.study.jedis.queue.RedisDelayingQueue;
import com.study.jedis.util.MessageConvert;
import com.study.jedis.util.TaskItem;

/**
 * @Author long
 * @Date 2019/9/8 17:11
 * 延时队列消费者线程
 */
public class DelayMessageConsumer extends Thread {
    /**
     * 消息队列
     */
    RedisDelayingQueue queue;
    /**
     * 延时消息处理器
     */
    private final static MessageHandle handle = new DelayMessageHandle();

    /**
     * 构造函数-初始化消息队列
     * @param queue
     */
    public DelayMessageConsumer(RedisDelayingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        this.queue.loop(handle);
    }
}
