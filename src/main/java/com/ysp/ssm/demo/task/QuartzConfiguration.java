package com.ysp.ssm.demo.task;

/**
 * Created by yuhuanxi on 16/8/27.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashMap;
import java.util.Map;


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
        //scheduler.setOverwriteExistingJobs(true);
        scheduler.setTriggers(cronTriggerFactoryBean().getObject(), cronTriggerFactoryBean2().getObject());
        scheduler.setJobDetails(jobDetailFactoryBean().getObject(), jobDetailFactoryBean2().getObject());
        return scheduler;
    }

    // Job is scheduled after every 5 sec

    /**
     * 触发器 1
     *
     * @return
     */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailFactoryBean().getObject());
        trigger.setStartDelay(3000);
        // TODO 该表达式可从数据库或者配置文件中获取
        trigger.setCronExpression("0/5 * * * * ?");
        return trigger;
    }

    /**
     * 触发器 2
     *
     * @return
     */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean2() {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailFactoryBean2().getObject());
        trigger.setStartDelay(3000);
        // TODO 该表达式可从数据库或者配置文件中获取
        trigger.setCronExpression("0/5 * * * * ?");
        return trigger;
    }

    // configure job bean

    /**
     * jobDetail 1
     *
     * @return
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(HelloworldJob.class);

        // bring real htask
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("htask", htask);
        jobDetail.setJobDataAsMap(map);
        jobDetail.setDurability(true);
        jobDetail.setName("helloworld");
        return jobDetail;
    }

    /**
     * jobDetail 2
     *
     * @return
     */
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean2() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(FuckworldJob.class);

        // bring real ftask
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ftask", ftask);
        jobDetail.setJobDataAsMap(map);

        jobDetail.setDurability(true);
        jobDetail.setName("fuckworld");
        return jobDetail;
    }

//    @Bean
//    HelloworldTask htask() {
//        return new HelloworldTask();
//    }
//
//    @Bean
//    FuckworldTask ftask() {
//        return new FuckworldTask();
//    }
}