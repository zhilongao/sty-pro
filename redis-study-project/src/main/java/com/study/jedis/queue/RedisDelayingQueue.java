package com.study.jedis.queue;

import com.study.jedis.handle.MessageHandle;
import redis.clients.jedis.Jedis;
import java.util.Set;
/**
 * @Author long
 * @Date 2019/9/7 16:56
 * 延时队列-> 通过zset实现
 */
public class RedisDelayingQueue {

    private Jedis jedis;
    private String queueKey;

    public RedisDelayingQueue(Jedis jedis, String queueKey) {
        this.jedis = jedis;
        this.queueKey = queueKey;
    }

    /**
     * 将数据放入到延时队列中
     * @param msg
     * @param score
     */
    public void delay(String msg, long score) {
        jedis.zadd(queueKey, score, msg);
    }

    /**
     * 轮询处理消息
     * @param handle
     */
    public void loop(MessageHandle handle) {
        while (!Thread.interrupted()) {
            // fixme 此处需要优化，保证操作的原子性
            // 获取一条数据(score最小的那条数据)
            Set values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            // 若是队列中没有任务，线程睡眠500毫秒
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String value = (String) values.iterator().next();
            // 从queueKey中移除该元素
            if (jedis.zrem(queueKey, value) > 0) {
                // 将消息交给回调接口来处理
                handle.handle(value);
            }
        }
    }
}









