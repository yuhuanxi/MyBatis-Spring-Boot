package com.ysp.ssm.demo.task;

/**
 * Created by yuhuanxi on 16/8/27.
 */

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class SecondTaskJob extends QuartzJobBean {

    SecondTask htask;

    public void setHtask(SecondTask task) {
        this.htask = task;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        htask.print();
    }

}
