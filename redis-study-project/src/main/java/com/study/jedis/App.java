package com.study.jedis;

import com.study.jedis.queue.*;
import com.study.jedis.service.DelayMessageConsumer;
import com.study.jedis.service.DelayMessageProducer;
import com.study.jedis.service.MessageConsumer;
import com.study.jedis.service.MessageProducer;
import com.study.jedis.util.MessageConvert;
import com.study.jedis.util.TaskItem;
import redis.clients.jedis.Jedis;

/**
 * @Author long
 * @Date 2019/9/8 15:00
 */
public class App {

    private static final String url = "192.168.25.150";
    private static final int port = 6379;

    public static void main(String[] args) {
        // 延时队列
        delayQueue();
        // testQueue();
    }



    private static void delayQueue() {
        Jedis jedis = new Jedis(url, port);
        // 延时队列
        RedisDelayingQueue queue = new RedisDelayingQueue(jedis, "q-demo");
        DelayMessageConsumer consumer = new DelayMessageConsumer(queue);
        DelayMessageProducer producer = new DelayMessageProducer(queue);
        producer.start();
        consumer.start();
        try {
            // 首先让producer线程执行完成
            producer.join();
            // 主线程睡眠6秒，等待consumer将消息消费完成
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        }  catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private static void testQueue() {
        // 构建queue
        String queueKey = "message_queue";
        Jedis jedis = new Jedis(url, port);
        // 消息转换器
        MessageConvert<TaskItem> convert = new MessageConvert<>();
        // 消息队列
        RedisQueue queue = new RedisQueue(jedis, queueKey, convert);
        // 生产者
        MessageProducer producer = new MessageProducer(queue);
        // 消费者
        MessageConsumer consumer = new MessageConsumer(queue);
        producer.start();
        consumer.start();
    }

}
