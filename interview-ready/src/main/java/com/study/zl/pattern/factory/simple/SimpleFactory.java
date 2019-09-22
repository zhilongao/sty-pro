package com.study.zl.pattern.factory.simple;


import com.study.zl.pattern.factory.Menniu;
import com.study.zl.pattern.factory.Milk;
import com.study.zl.pattern.factory.Telunsu;
import com.study.zl.pattern.factory.Yili;

/**
 * @Author long
 * @Date 2019/3/2 16:18
 * // 简单工厂:特点是通过用户的传入需求值来返回特定的产品,所有种类的产品都交由
 * 一个工厂来完成,不专业。
 */
public class SimpleFactory {

    public Milk getMilk(String name){
        if ("特仑苏".equals(name)) {
            return new Telunsu();
        } else if ("蒙牛".equals(name)) {
            return new Menniu();
        } else if ("伊利".equals(name)) {
            return new Yili();
        } else {
            return null;
        }
    }
}
