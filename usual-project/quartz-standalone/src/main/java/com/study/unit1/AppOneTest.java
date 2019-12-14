package com.study.unit1;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @Author long
 * @Date 2019/12/8 16:38
 */
public class AppOneTest {

    public static void main(String[] args) {
        try {
            // 1. 通过工厂获取调度程序
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 2. 开启调度程序
            scheduler.start();
            // 3. 创建JobDetail
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("key1", "value1");
            jobDataMap.put("key2", "value2");
            jobDataMap.put("key3", "value3");
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                                    .withIdentity("job1", "group1")
                                    .usingJobData(jobDataMap)
                                    .build();
            // 4. 创建Trigger
            JobDataMap jobDataMap1 = new JobDataMap();
            jobDataMap1.put("key4", "value4");
            jobDataMap1.put("key3", "333");
            Trigger trigger = TriggerBuilder.newTrigger()
                                    .withIdentity("trigger1", "group1")
                                    .startNow()// 立即开始
                                    .withSchedule(// 每四秒执行一次,一直执行
                                            SimpleScheduleBuilder.simpleSchedule()
                                                    .withIntervalInSeconds(4)
                                                    .repeatForever()
                                    )
                                    .usingJobData(jobDataMap1)
                                    .build();
            // 5. 调度程序调度任务
            scheduler.scheduleJob(jobDetail, trigger);
            // 6. 关闭调度程序
            // scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    // 定义一个简单的job
    public static class HelloJob implements Job{

        private String key1;

        private String key2;

        private String key3;

        private String key4;


        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobKey detailKey = context.getJobDetail().getKey();
            System.out.println(detailKey);
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            System.out.println("---------------------------------");
            Set<Map.Entry<String, Object>> entries = jobDataMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                String key = entry.getKey();
                Object value = entry.getValue();
                System.out.printf("key=%s,value=%s\n", key, value);
            }
            System.out.println("---------------------------------");
            JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
            System.out.println(this);
            System.out.println("---------------------------------");
            long time = System.currentTimeMillis();
            System.out.println("now:" + time);
        }

        public void setKey1(String key1) {
            this.key1 = key1;
        }

        public void setKey2(String key2) {
            this.key2 = key2;
        }

        public void setKey3(String key3) {
            this.key3 = key3;
        }

        public void setKey4(String key4) {
            this.key4 = key4;
        }

        @Override
        public String toString() {
            return this.key1 + "--" + this.key2 + "--" + this.key3 + "--" + this.key4;
        }
    }
}
