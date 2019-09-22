package com.study.zl.pattern.singleton.seria;

import java.io.Serializable;

/**
 * @Author long
 * @Date 2019/3/3 8:36
 */
public class SerializableInstance implements Serializable {

    private static final SerializableInstance INSTANCE = new SerializableInstance();

    private SerializableInstance(){}

    public static SerializableInstance getInstance(){
        return INSTANCE;
    }

    // 解决序列化和反序列化造成的对象创建不一致
    private Object readResolve(){
        return INSTANCE;
    }
}
