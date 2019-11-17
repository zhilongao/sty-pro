package com.study.listener;

import org.quartz.*;

/**
 * @Author long
 * @Date 2019/11/17 10:05
 * 定义 SchedulerListener
 */
public class MySchedulerListener implements SchedulerListener {

    @Override
    public void jobScheduled(Trigger trigger) {
        JobKey key = trigger.getJobKey();
        System.out.printf("[MySchedulerListener] group:%s, name:%s jobScheduled", key.getGroup(), key.getName());
    }

    @Override
    public void jobUnscheduled(TriggerKey triggerKey) {
        System.out.println(triggerKey + " is being unscheduled");
    }

    @Override
    public void triggerFinalized(Trigger trigger) {
        System.out.println("Trigger is finished for " + trigger.getJobKey().getName());
    }

    @Override
    public void triggerPaused(TriggerKey triggerKey) {
        System.out.println(triggerKey + " is being paused");
    }

    @Override
    public void triggersPaused(String triggerGroup) {
        System.out.println("trigger group "+triggerGroup + " is being paused");
    }

    @Override
    public void triggerResumed(TriggerKey triggerKey) {
        System.out.println(triggerKey + " is being resumed");
    }

    @Override
    public void triggersResumed(String triggerGroup) {
        System.out.println("trigger group "+triggerGroup + " is being resumed");
    }

    public void jobAdded(JobDetail jobDetail) {
        System.out.println(jobDetail.getKey()+" is added");
    }

    public void jobDeleted(JobKey jobKey) {
        System.out.println(jobKey+" is deleted");
    }

    public void jobPaused(JobKey jobKey) {
        System.out.println(jobKey+" is paused");
    }

    public void jobsPaused(String jobGroup) {
        System.out.println("job group "+jobGroup+" is paused");
    }

    public void jobResumed(JobKey jobKey) {
        System.out.println(jobKey+" is resumed");
    }

    public void jobsResumed(String jobGroup) {
        System.out.println("job group "+jobGroup+" is resumed");
    }

    public void schedulerError(String msg, SchedulerException cause) {
        System.out.println(msg + cause.getUnderlyingException().getStackTrace());
    }

    public void schedulerInStandbyMode() {
        System.out.println("scheduler is in standby mode");
    }

    public void schedulerStarted() {
        System.out.println("scheduler has been started");
    }


    public void schedulerStarting() {
        System.out.println("scheduler is being started");
    }

    public void schedulerShutdown() {
        System.out.println("scheduler has been shutdown");
    }

    public void schedulerShuttingdown() {
        System.out.println("scheduler is being shutdown");
    }

    public void schedulingDataCleared() {
        System.out.println("scheduler has cleared all data");
    }
}
