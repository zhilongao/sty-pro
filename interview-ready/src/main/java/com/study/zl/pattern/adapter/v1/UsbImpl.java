package com.study.zl.pattern.adapter.v1;

/**
 * @Author long
 * @Date 2019/3/20 21:59
 */
public class UsbImpl implements Usb {

    @Override
    public void isUsb() {
        System.out.println("这个是usb接口");
    }
}
