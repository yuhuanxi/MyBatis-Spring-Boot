package com.ysp.ssm.demo.task;

import com.ysp.ssm.demo.service.ICityService;
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

        // 从中可以获取到 service
        ICityService cityService = (ICityService) jobExecutionContext.getJobDetail().getJobDataMap().get("cityService");
        System.out.println(cityService.getById(56L));

        firstTask.print();
    }

}
