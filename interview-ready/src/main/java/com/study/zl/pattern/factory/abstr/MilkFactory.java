package com.study.zl.pattern.factory.abstr;

import com.study.zl.pattern.factory.Milk;
import com.study.zl.pattern.factory.func.MenniuFactory;
import com.study.zl.pattern.factory.func.TelunsuFactory;
import com.study.zl.pattern.factory.func.YiliFactory;


/**
 * @Author long
 * @Date 2019/3/2 16:55
 */
public class MilkFactory extends AbstractMilkFactory {

    @Override
    Milk getMenniu() {
        // return new Menniu();
        // 生产某种牛奶的任务可以交由特定的工厂来完成啦
        return new MenniuFactory().getMilk();
    }

    @Override
    Milk getTelunsu() {
        // return new Telunsu();
        return new TelunsuFactory().getMilk();
    }

    @Override
    Milk getYili() {
        // return new Yili();
        return new YiliFactory().getMilk();
    }
}
