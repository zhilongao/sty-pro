package com.study.zl.pattern.adapter.v2;

/**
 * @Author long
 * @Date 2019/3/20 22:20
 */
public class Ps2Adapter implements Ps2 {

    private Usb usb;

    public Ps2Adapter(Usb usb){
        this.usb = usb;
    }

    @Override
    public void isPs2() {
        usb.isUsb();
    }
}
