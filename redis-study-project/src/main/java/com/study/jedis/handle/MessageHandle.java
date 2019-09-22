package com.study.jedis.handle;

/**
 * @Author long
 * @Date 2019/9/8 17:21
 * 消息处理回调接口
 */
public interface MessageHandle {

    void handle(String item);
}
