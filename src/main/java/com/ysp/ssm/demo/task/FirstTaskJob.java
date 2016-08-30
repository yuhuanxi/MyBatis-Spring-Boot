package com.ysp.ssm.demo.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by yuhuanxi on 16/8/27.
 */
public class FirstTaskJob extends QuartzJobBean {

    FirstTask firstTask;

    public void setFirstTask(FirstTask firstTask) {
        this.firstTask = firstTask;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        firstTask.print();
    }
}
