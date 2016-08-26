package com.ysp.ssm.demo.task;

/**
 * Created by yuhuanxi on 16/8/27.
 */

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class HelloworldJob extends QuartzJobBean {

    HelloworldTask htask;

    public void setHtask(HelloworldTask task) {
        this.htask = task;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        htask.print();
    }

}
