package com.study.zl.jvm;

/**
 * @Author long
 * @Date 2019/8/16 14:09
 * VM Args:-Xss128k
 */
public class HeadOOM {

    private int stackLength;

    public void stackLeek() {
        stackLength ++;
        stackLeek();
    }

    public static void main(String[] args) throws Throwable{
        HeadOOM oom = new HeadOOM();
        try {
            oom.stackLeek();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
