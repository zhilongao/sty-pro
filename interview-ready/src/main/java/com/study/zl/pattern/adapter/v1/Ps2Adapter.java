package com.study.zl.pattern.adapter.v1;

/**
 * @Author long
 * @Date 2019/3/20 22:02
 */
public class Ps2Adapter extends UsbImpl implements Ps2 {

    @Override
    public void isPs2() {
        isUsb();
    }
}
