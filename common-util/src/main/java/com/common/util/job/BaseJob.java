package com.common.util.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 基本任务接口，实现quartz的job接口，
 */
public interface BaseJob extends Job {

	void execute(JobExecutionContext context) throws JobExecutionException;

}

