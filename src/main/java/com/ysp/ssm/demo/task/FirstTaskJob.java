package com.ysp.ssm.demo.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by yuhuanxi on 16/8/27.
 */
public class FirstTaskJob extends QuartzJobBean {

    FirstTask ftask;

    public void setFtask(FirstTask ftask) {
        this.ftask = ftask;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ftask.print();
    }
}
