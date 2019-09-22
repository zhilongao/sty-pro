package com.study.zl.pattern.delegate.boss;

/**
 * @Author long
 * @Date 2019/3/6 22:24
 */
public class EmployeeA implements Employee {

    @Override
    public void writeCode(String command) {
        System.out.println("我是程序员A,我要开始工作啦,我的任务是:" + command);
    }
}
