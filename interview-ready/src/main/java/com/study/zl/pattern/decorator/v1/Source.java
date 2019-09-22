package com.study.zl.pattern.decorator.v1;

/**
 * @Author long
 * @Date 2019/3/20 21:31
 */
public class Source implements SourceAble {

    @Override
    public void operation() {
        System.out.println("Source最原始的方法");
    }
}
