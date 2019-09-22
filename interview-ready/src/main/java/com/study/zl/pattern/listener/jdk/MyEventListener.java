package com.study.zl.pattern.listener.jdk;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @Author long
 * @Date 2019/9/13 11:00
 * 扩展的监听器接口
 */
public interface MyEventListener extends EventListener {
    /**
     * 处理EventObject
     * @param event
     */
    void handleEvent(EventObject event);
}
