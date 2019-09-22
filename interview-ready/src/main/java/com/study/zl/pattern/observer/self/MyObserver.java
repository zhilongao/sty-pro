package com.study.zl.pattern.observer.self;

/**
 * @Author long
 * @Date 2019/9/13 10:34
 */
public interface MyObserver {

    void update(MyObservable o, Object args);
}
