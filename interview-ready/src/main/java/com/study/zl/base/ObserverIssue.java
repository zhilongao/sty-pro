package com.study.zl.base;

import java.util.Observable;
import java.util.Observer;

/**
 * @Author long
 * @Date 2019/7/26 15:30
 * 观察者模式:订阅者被动感知变化
 */
public class ObserverIssue {

    public static void main(String[] args) {
        // 定义一个发布者
        MyObserver myObserver = new MyObserver();
        // 注册观察者
        myObserver.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                System.out.println("订阅者1号");
                System.out.println(value);
            }
        });
        myObserver.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object value) {
                System.out.println("订阅者2号");
                System.out.println(value);
            }
        });
        // 设置变化
        myObserver.setChanged();
        // 通知观察者
        myObserver.notifyObservers("hello,world");
    }
}

class MyObserver extends Observable{
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
}