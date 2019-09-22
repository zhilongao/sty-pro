package com.study.zl.pattern.decorator.v1;

/**
 * @Author long
 * @Date 2019/3/20 21:48
 */
public class App {

    public static void main(String[] args) {
        SourceAble source = new Source();
        source.operation();
        System.out.println("================");
        SourceAble decorateA = new SourceDecorateA(source);
        decorateA.operation();
        System.out.println("================");
        SourceAble decorateB = new SourceDecorateB(decorateA);
        decorateB.operation();
    }
}
