package com.study.zl.pattern.template;

/**
 * @Author long
 * @Date 2019/3/5 22:54
 */
public class App {
    public static void main(String[] args) {
        Account a1 = new MarketAccount();
        System.out.println("货币市场利息额为:" + a1.calculateInterest());

        Account a2 = new CDAccount();
        System.out.println("定期货币利息额为:" + a2.calculateInterest());
    }
}
