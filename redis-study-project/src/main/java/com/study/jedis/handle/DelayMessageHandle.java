package com.study.jedis.handle;

import com.study.jedis.util.MessageConvert;
import com.study.jedis.util.TaskItem;

/**
 * @Author long
 * @Date 2019/9/8 19:49
 * 延时消息处理器
 */
public class DelayMessageHandle implements MessageHandle {

    private static final MessageConvert<TaskItem> convert = new MessageConvert<TaskItem>();

    @Override
    public void handle(String item) {
        TaskItem delayTaskItem = convert.stringToObject(item, TaskItem.class);
        System.out.println(delayTaskItem);
    }
}
