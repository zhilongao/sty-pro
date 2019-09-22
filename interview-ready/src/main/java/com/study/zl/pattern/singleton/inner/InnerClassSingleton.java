package com.study.zl.pattern.singleton.inner;

/**
 * @Author long
 * @Date 2019/3/9 22:56
 */
public class InnerClassSingleton {

    private InnerClassSingleton(){
        // 防止反射入侵
        if(getInstance() != null){
            throw new RuntimeException("反射异常");
        }
    }

    public static InnerClassSingleton getInstance(){
        return InnerClass.INSTANCE;
    }

    private static class InnerClass{
        private static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }
}
