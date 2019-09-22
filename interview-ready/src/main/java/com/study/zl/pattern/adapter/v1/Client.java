package com.study.zl.pattern.adapter.v1;

/**
 * @Author long
 * @Date 2019/3/20 21:58
 */
public class Client {

    public static void main(String[] args) {
        Ps2 ps2 = new Ps2Adapter();
        ps2.isPs2();
    }
}
