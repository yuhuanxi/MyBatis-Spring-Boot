package com.ysp.ssm.demo.task;

import com.ysp.ssm.demo.service.ICityService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author: shipeng.yu
 * @time: 16/9/3-下午10:03
 * @version: 1.0
 * @description: 第一个 JOB
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
