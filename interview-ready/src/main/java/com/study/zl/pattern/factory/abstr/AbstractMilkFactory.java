package com.study.zl.pattern.factory.abstr;


import com.study.zl.pattern.factory.Milk;

/**
 * @Author long
 * @Date 2019/3/2 16:54
 */
public abstract class AbstractMilkFactory {

    abstract Milk getTelunsu();

    abstract Milk getMenniu();

    abstract Milk getYili();
}
