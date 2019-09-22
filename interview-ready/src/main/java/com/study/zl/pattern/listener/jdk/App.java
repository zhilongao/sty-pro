package com.study.zl.pattern.listener.jdk;

import java.util.EventObject;

/**
 * @Author long
 * @Date 2019/9/13 11:03
 */
public class App {
    public static void main(String[] args) {
        // 事件源
        MyEventSource eventSource = new MyEventSource();
        // 创建事件监听器->处理EventObject
        StartEventListener startEventListener = new StartEventListener();
        ClosedEventListener closedEventListener = new ClosedEventListener();
        // 添加事件监听器
        eventSource.addEventListener(startEventListener);
        eventSource.addEventListener(closedEventListener);
        // 创建事件对象
        EventObject startEventObject = new EventObject("start");
        EventObject closedEventObject = new EventObject("closed");
        // 事件源->通知事件监听者来处理
        eventSource.notifyListenerEvents(startEventObject);
        eventSource.notifyListenerEvents(closedEventObject);
    }
}
