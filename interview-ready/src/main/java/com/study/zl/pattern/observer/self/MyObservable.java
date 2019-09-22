package com.study.zl.pattern.observer.self;

import lombok.Data;

import java.util.Vector;

/**
 * @Author long
 * @Date 2019/9/13 10:35
 */
@Data
public class MyObservable {

    private boolean state = false;
    private Vector<MyObserver> observers;

    public MyObservable() {
        observers = new Vector<>();
    }


    public void add(MyObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void notifyAllObservers(Object args) {
        Object[] arrayLocal;
        synchronized (this) {
            if (!state) {
                return;
            }
            arrayLocal = observers.toArray();
            state = false;
        }
        for (int i = arrayLocal.length - 1; i >= 0 ; i--) {
            ((MyObserver)arrayLocal[i]).update(this, args);
        }
    }
}
