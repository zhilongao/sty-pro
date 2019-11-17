package com.study.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * @Author long
 * @Date 2019/11/17 10:12
 */
public class MyTriggerListener implements TriggerListener {

    private String name;


    public MyTriggerListener(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    // Trigger 被触发，Job 上的 execute() 方法将要被执行时
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String triggerName = trigger.getKey().getName();
        System.out.println("Method 11111 " + triggerName + " was fired");
    }

    // 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法
    // 返回true时，这个任务不会被触发
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        String triggerName = trigger.getKey().getName();
        System.out.println("Method 222222 " + triggerName + " was not vetoed");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        String triggerName = trigger.getKey().getName();
        System.out.println("Method 333333 " + triggerName + " misfired");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext, Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
        String triggerName = trigger.getKey().getName();
        System.out.println("Method 444444 " + triggerName + " is complete");
        System.out.println("------------");
    }
}
