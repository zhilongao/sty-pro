package com.study.zl.pattern.observer.self;

/**
 * @Author long
 * @Date 2019/9/13 10:42
 */
public class App {
    public static void main(String[] args) {
        MyObservable myObservable = new MyObservable();

        MessageObserver observer1 = new MessageObserver();
        MessageObserver observer2 = new MessageObserver();

        myObservable.add(observer1);
        myObservable.add(observer2);

        myObservable.setState(true);
        myObservable.notifyAllObservers("xixihaha");
    }
}

class MessageObserver implements MyObserver {
    @Override
    public void update(MyObservable o, Object args) {
        System.out.println("接收到消息:" + args);
    }
}