package com.study.zl.thread;

/**
 * @Author long
 * @Date 2019/8/16 16:15
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        // 初始化一个local变量
        ThreadLocal<String> local = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "hello,world";
            }
        };

        local.get();
    }
}
