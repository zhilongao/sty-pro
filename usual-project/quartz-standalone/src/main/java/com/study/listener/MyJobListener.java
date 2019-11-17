package com.study.listener;

import org.quartz.*;

/**
 * @Author long
 * @Date 2019/11/17 9:53
 * 定义JobListener
 */
public class MyJobListener implements JobListener {

    @Override
    public String getName() {
        String simpleName = getClass().getSimpleName();
        System.out.println("-->获取到监听器名称:" + simpleName);
        return simpleName;
    }

    // 任务即将执行
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.printf("group:%s, name:%s 任务即将执行\n", key.getGroup(), key.getName());
    }

    // 任务被否决
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.printf("group:%s, name:%s 任务被否决\n", key.getGroup(), key.getName());
    }

    // 任务执行结束
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        JobKey key = jobExecutionContext.getJobDetail().getKey();
        System.out.printf("group:%s, name:%s 任务执行结束\n", key.getGroup(), key.getName());
    }
}
