package com.study.jedis.util;

import java.io.Serializable;

/**
 * @Author long
 * @Date 2019/9/8 15:46
 * 任务
 */
public class TaskItem<T> implements Serializable {

    public String id;
    public T msg;
    public long score = 0;

    @Override
    public String toString() {
        return this.id + ":" + this.msg + ":" + this.score;
    }
}