package com.study.zl.pattern.decorator.v1;

/**
 * @Author long
 * @Date 2019/3/20 21:34
 */
public class SourceDecorateB implements SourceAble {

    private SourceAble sourceAble;

    public SourceDecorateB(SourceAble sourceAble){
        this.sourceAble = sourceAble;
    }

    @Override
    public void operation() {
        System.out.println("SourceDecorateB before");
        sourceAble.operation();
        System.out.println("SourceDecorateB after");
    }
}
