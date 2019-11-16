package com.study.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @Author long
 * @Date 2019/11/16 17:07
 */
public class JobService {

    public void executeQuartzJob() {
        try {
            // 1. 创建任务job
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("userIds", "1110,1112");
            jobDataMap.put("message", "some message");
            JobDetail jobDetail = JobBuilder.newJob(SimpleJob.class)
                    .withIdentity("notifyCustomerJob", "notifyMessage")
                    .usingJobData(jobDataMap)
                    .build();
            // 2. 创建触发器trigger
            SimpleTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger")
                    .startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                    .build();
            // 3. 创建调度器scheduler
            StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            // 4. 开启调度器
            scheduler.start();
            // 5. 执行Job
            scheduler.scheduleJob(jobDetail, trigger);
            // 6. 关闭调度器
            // scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
