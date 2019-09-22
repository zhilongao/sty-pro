package com.study.zl.pattern.adapter.v2;

/**
 * @Author long
 * @Date 2019/3/20 22:17
 */
public class Client {
    public static void main(String[] args) {

        Ps2 ps2 = new Ps2Adapter(new UsbImpl());
        ps2.isPs2();
    }
}
