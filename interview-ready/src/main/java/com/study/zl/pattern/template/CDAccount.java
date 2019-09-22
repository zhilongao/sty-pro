package com.study.zl.pattern.template;

/**
 * @Author long
 * @Date 2019/3/5 22:53
 */
public class CDAccount extends Account {

    @Override
    protected String doAccountType() {
        return "定期账户";
    }

    @Override
    protected double doAccountRate() {
        return 0.6;
    }
}
