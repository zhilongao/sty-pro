package com.study.zl.pattern.listener.jdk;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;

/**
 * @Author long
 * @Date 2019/9/13 11:03
 * 事件源->添加 删除 通知
 */
public class MyEventSource {

    /**
     * 监听器列表
     */
    private Vector<EventListener> listeners = new Vector<>();

    /**
     * 注册监听器
     * @param eventListener
     */
    public void addEventListener(EventListener eventListener) {
        if (!listeners.contains(eventListener)) {
            listeners.add(eventListener);
        }
    }

    /**
     * 移除监听器
     * @param eventListener
     */
    public void removeEventListener(EventListener eventListener) {
        listeners.remove(eventListener);
    }

    /**
     * 接收外部的事件->将事件交给事件监听者来处理
     * @param event
     */
    public void notifyListenerEvents(EventObject event) {
        for (EventListener eventListener : listeners) {
            ((MyEventListener)eventListener).handleEvent(event);
        }
    }
}
