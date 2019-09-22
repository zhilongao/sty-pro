package com.study.zl.pattern.factory.func;

import com.study.zl.pattern.factory.Menniu;
import com.study.zl.pattern.factory.Milk;

/**
 * @Author long
 * @Date 2019/3/2 16:49
 */
public class MenniuFactory implements FuncMilkFactory {

    @Override
    public Milk getMilk() {
        return new Menniu();
    }
}
