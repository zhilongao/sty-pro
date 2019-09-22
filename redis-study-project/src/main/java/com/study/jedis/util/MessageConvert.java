package com.study.jedis.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.lang.reflect.Type;

/**
 * @Author long
 * @Date 2019/9/8 15:45
 * 消息转换器
 */
public class MessageConvert<T> {

    private Type taskType = new TypeReference<T>(){}.getType();

    /**
     * 将给定的消息转换为string类型
     * @param t
     * @return
     */
    public String messageToString(T t) {
        return JSON.toJSONString(t);
    }

    /**
     * 将string类型的消息转换为T类型
     * @param message
     * @return
     */
    public T stringToObject(String message, Class clazz) {
        return (T)JSON.parseObject(message, clazz);
    }
}
