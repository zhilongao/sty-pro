package com.study.zl.jvm;

/**
 * @Author long
 * @Date 2019/8/16 14:14
 * VM Args: -Xss2M
 */
public class JavaVMStackOOM {

    private void dontStop(){
        while (true) {

        }
    }


    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();;
    }


}
