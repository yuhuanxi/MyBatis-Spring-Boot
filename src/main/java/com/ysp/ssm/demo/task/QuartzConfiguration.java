package com.ysp.ssm.demo.task;

/**
 * Created by yuhuanxi on 16/8/27.
 * <p>
 * Quartz 配置类
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {

    @Autowired
    private FirstTask firstTask;

    @Autowired
    private SecondTask secondTask;

    /**
     * 注册 scheduled
     *
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setOverwriteExistingJobs(true);
        scheduler.setTriggers(firstTask.firstCronTriggerFactoryBean.getObject(), secondTask.cronTriggerFactoryBean.getObject());
        scheduler.setJobDetails(firstTask.firstJobDetailFactoryBean.getObject(), secondTask.jobDetailFactoryBean.getObject());
        return scheduler;
    }

}