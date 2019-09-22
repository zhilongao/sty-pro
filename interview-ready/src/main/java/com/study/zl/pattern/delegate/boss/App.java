package com.study.zl.pattern.delegate.boss;

/**
 * @Author long
 * @Date 2019/3/6 22:22
 * 委派模式测试类
 */
public class App {

    public static void main(String[] args) {
        String command1 = "login";
        String command2 = "thread";

        Leader leader = new Leader();
        leader.doWork(command1);
        leader.doWork(command2);
    }
}
