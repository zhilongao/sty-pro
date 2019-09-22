package com.study.zl.pattern.singleton.lazy;

/**
 * @Author long
 * @Date 2019/3/9 22:49
 */
public class LazyDoubleCheckSingleton {

    private static volatile LazyDoubleCheckSingleton singleton;

    private LazyDoubleCheckSingleton(){}

    private static LazyDoubleCheckSingleton getInstance(){
        if (singleton == null) {
            synchronized (LazyDoubleCheckSingleton.class){
                if (singleton == null) {
                    singleton = new LazyDoubleCheckSingleton();
                }
            }
        }
        return singleton;
    }
}
