package com.study.zl.pattern.factory.func;


import com.study.zl.pattern.factory.Milk;
import com.study.zl.pattern.factory.Telunsu;

/**
 * @Author long
 * @Date 2019/3/2 16:51
 */
public class TelunsuFactory implements FuncMilkFactory {

    @Override
    public Milk getMilk() {
        return new Telunsu();
    }
}
