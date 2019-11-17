package com.study.test;

import com.study.scheduler.FirstScheduler;

/**
 * @Author long
 * @Date 2019/11/16 17:08
 */
public class QuartzTest {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        FirstScheduler.executeJob();
    }
}
