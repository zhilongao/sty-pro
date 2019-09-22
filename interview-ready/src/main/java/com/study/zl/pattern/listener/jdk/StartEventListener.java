package com.study.zl.pattern.listener.jdk;

import java.util.EventObject;

/**
 * @Author long
 * @Date 2019/9/13 11:21
 * 启动事件监听器
 */
public class StartEventListener implements MyEventListener {

    @Override
    public void handleEvent(EventObject event) {
        Object source = event.getSource();
        if (source.equals("start")) {
            System.out.println("context has start!");
        }
    }
}
