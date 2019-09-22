package com.study.zl.pattern.factory.func;


import com.study.zl.pattern.factory.Milk;
import com.study.zl.pattern.factory.Yili;

/**
 * @Author long
 * @Date 2019/3/2 16:50
 */
public class YiliFactory implements FuncMilkFactory {

    @Override
    public Milk getMilk() {
        return new Yili();
    }
}
