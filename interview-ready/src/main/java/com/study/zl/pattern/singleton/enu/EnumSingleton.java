package com.study.zl.pattern.singleton.enu;

/**
 * @Author long
 * @Date 2019/3/9 23:00
 */
public enum  EnumSingleton {
    INSTANCE;

    // 该属性为扩展点
    private Object data;

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
