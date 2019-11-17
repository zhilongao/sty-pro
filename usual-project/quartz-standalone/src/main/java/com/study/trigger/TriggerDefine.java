package com.study.trigger;

import org.quartz.*;

/**
 * @Author long
 * @Date 2019/11/17 9:37
 * 定义触发器(trigger)
 */
public class TriggerDefine {

    public static void createTrigger1() {
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(
                        CalendarIntervalScheduleBuilder
                                .calendarIntervalSchedule()
                                .withIntervalInDays(1)// 每天执行一次
                )
                .build();
    }

    public static void createTrigger2(){
        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group2")
                .startNow()
                .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                        .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(9, 0))// 每天9点开始
                        .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(16, 0)) //16：00 结束
                        .onDaysOfTheWeek(1, 2, 3, 4, 5) //周一至周五执行
                        .withIntervalInHours(1) //每间隔1小时执行一次
                        .withRepeatCount(100)//最多重复100次（实际执行100+1次）
                )
                .build();
    }
}
