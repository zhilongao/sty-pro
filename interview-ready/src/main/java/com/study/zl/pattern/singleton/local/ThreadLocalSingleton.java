package com.study.zl.pattern.singleton.local;

/**
 * @Author long
 * @Date 2019/3/10 8:41
 */
public class ThreadLocalSingleton {

    private static final ThreadLocal<ThreadLocalSingleton> instance =
            new ThreadLocal<ThreadLocalSingleton>(){
                @Override
                protected ThreadLocalSingleton initialValue() {
                    return new ThreadLocalSingleton();
                }
            };

    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return instance.get();
    }
}
