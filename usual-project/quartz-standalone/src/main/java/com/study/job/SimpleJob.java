package com.study.job;

import org.quartz.*;

/**
 * @Author long
 * @Date 2019/11/16 17:06
 */
public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        String group = key.getGroup();
        String name = key.getName();
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.printf("execute job, group=%s, name=%s\n", group, name);
        String userIds = jobDataMap.getString("userIds");
        String message = jobDataMap.getString("message");
        System.out.printf("userIds = %s, message = %s", userIds, message);
    }
}
