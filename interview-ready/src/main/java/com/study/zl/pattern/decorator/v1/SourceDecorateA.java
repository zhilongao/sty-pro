package com.study.zl.pattern.decorator.v1;

/**
 * @Author long
 * @Date 2019/3/20 21:32
 */
public class SourceDecorateA implements SourceAble {

    private SourceAble sourceAble;

    public SourceDecorateA(SourceAble sourceAble){
        this.sourceAble = sourceAble;
    }

    @Override
    public void operation() {
        System.out.println("SourceDecorateA before");
        sourceAble.operation();
        System.out.println("SourceDecorateA after");
    }
}
