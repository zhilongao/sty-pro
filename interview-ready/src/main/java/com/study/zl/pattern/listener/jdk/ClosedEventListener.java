package com.study.zl.pattern.listener.jdk;

import java.util.EventObject;

/**
 * @Author long
 * @Date 2019/9/13 11:24
 * 关闭事件监听器
 */
public class ClosedEventListener implements MyEventListener {

    @Override
    public void handleEvent(EventObject event) {
        Object source = event.getSource();
        if (source.equals("closed")) {
            System.out.println("context has closed!");
        }
    }
}
