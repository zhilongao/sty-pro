package com.study.test;

import com.study.job.JobService;

/**
 * @Author long
 * @Date 2019/11/16 17:08
 */
public class QuartzTest {
    public static void main(String[] args) {
        new JobService().executeQuartzJob();
    }
}
