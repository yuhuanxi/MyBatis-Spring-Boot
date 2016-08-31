package com.ysp.ssm.demo.task;

import com.ysp.ssm.demo.service.ICityService;
import com.ysp.ssm.demo.task.dynamic.ScheduledActionRunnerJobDetailFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
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
