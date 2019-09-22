package com.study.zl.thread.info;

import java.util.concurrent.TimeUnit;

/**
 * @Author long
 * @Date 2019/8/25 10:28
 */
public class SleepUtils {

    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
