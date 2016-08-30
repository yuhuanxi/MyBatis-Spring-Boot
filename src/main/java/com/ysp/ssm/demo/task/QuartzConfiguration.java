package com.ysp.ssm.demo.task;

/**
 * Created by yuhuanxi on 16/8/27.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;


@Configuration
public class QuartzConfiguration {

    @Autowired
    private HelloworldTask htask;

    @Autowired
    private FuckworldTask ftask;

    // register all triggers and details here
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setOverwriteExistingJobs(true);
        scheduler.setTriggers(htask.cronTriggerFactoryBean.getObject(), ftask.fCronTriggerFactoryBean.getObject());
        scheduler.setJobDetails(htask.jobDetailFactoryBean.getObject(), ftask.fJobDetailFactoryBean.getObject());
        return scheduler;
    }

}