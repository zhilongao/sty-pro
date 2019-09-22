package com.study.zl.pattern.adapter.v2;

/**
 * @Author long
 * @Date 2019/3/20 22:19
 */
public class UsbImpl implements Usb {

    @Override
    public void isUsb() {
        System.out.println("我是Usb接口");
    }
}
