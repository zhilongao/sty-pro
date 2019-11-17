package com.study.jdktimer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author long
 * @Date 2019/11/17 10:20
 */
public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Date executeTime = new Date(this.scheduledExecutionTime());
                String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                System.out.println("任务执行了：" + dateStr);
            }
        };
        timer.schedule(timerTask, 5000L, 1000L);
    }
}
