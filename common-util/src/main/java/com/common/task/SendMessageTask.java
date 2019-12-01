package com.common.task;

import com.common.util.job.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author long
 * @Date 2019/11/17 18:12
 * 短信发送任务->实现BaseJob接口
 */
public class SendMessageTask implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.printf("send message to user in %s\n", sdf.format(date));
    }
}
