package com.study.scheduler;

import com.study.job.SimpleJob;
import com.study.listener.MyJobListener;
import com.study.listener.MySchedulerListener;
import com.study.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * @Author long
 * @Date 2019/11/17 9:48
 * 任务调度器
 */
public class FirstScheduler {

    // 任务调度器，执行job
    public static void executeJob() {
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
            // 5. 绑定关系
            scheduler.scheduleJob(jobDetail, trigger);
            // 6. 为任务调度器绑定listener(非必须)
            // scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());
            // 7. 为任务调度器绑定listener(非必须)
            // scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());

            /**************************TriggerListener****************************/
            // 创建并注册一个全局的Trigger Listener
            scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("myListener1"), EverythingMatcher.allTriggers());
            // 创建并注册一个局部的Trigger Listener
            scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("myListener2"), KeyMatcher.keyEquals(TriggerKey.triggerKey("trigger1", "gourp1")));
            // 创建并注册一个特定组的Trigger Listener
            GroupMatcher<TriggerKey> matcher = GroupMatcher.triggerGroupEquals("gourp1");
            scheduler.getListenerManager().addTriggerListener(new MyTriggerListener("myListener3"), matcher);
            // 8. 关闭调度器
            // scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
