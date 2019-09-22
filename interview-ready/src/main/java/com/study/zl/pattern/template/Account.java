package com.study.zl.pattern.template;

/**
 * @Author long
 * @Date 2019/3/5 22:47
 * 模板方法顶层类
 */
public abstract class Account {
    // 计算利息
    public final double calculateInterest(){
        double amount = calculateAmount();
        String type = doAccountType();
        double rate = doAccountRate();
        return amount * rate;
    }

    // 计算账户类型
    protected abstract String doAccountType();
    // 计算利率
    protected abstract double doAccountRate();
    // 计算钱数
    private double calculateAmount(){
        return 12000.25;
    }

}
