package com.study.zl.pattern.template;

/**
 * @Author long
 * @Date 2019/3/5 22:52
 */
public class MarketAccount extends Account {

    @Override
    protected double doAccountRate() {
        return 0.33;
    }

    @Override
    protected String doAccountType() {
        return "货币市场账户";
    }
}
