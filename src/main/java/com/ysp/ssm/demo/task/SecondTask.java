package com.ysp.ssm.demo.task;

/**
 * Created by yuhuanxi on 16/8/27.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration // // 因为这里要注册多个 Bean ,用 @Configuration 注解,而不用 @Component
public class SecondTask {

    public void print() {
        System.out.println("doing second task job...");
    }

    @Bean(name = "jobDetailFactoryBean")
    public JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(SecondTaskJob.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("htask", new SecondTask());
        jobDetail.setJobDataAsMap(map);
        jobDetail.setDurability(true);
        jobDetail.setName("helloworld");
        return jobDetail;
    }

    @Bean(name = "cronTriggerFactoryBean")
    public CronTriggerFactoryBean cronTriggerFactoryBean() {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetailFactoryBean().getObject());
        trigger.setStartDelay(3000);
        // TODO 该表达式可从数据库或者配置文件中获取
        trigger.setCronExpression("0/5 * * * * ?");
        return trigger;
    }

    @Autowired
    @Qualifier("jobDetailFactoryBean")
//    @Resource(name = "jobDetailFactoryBean") // TODO 这里也不能使用 @Resource,具体原因待定
            JobDetailFactoryBean jobDetailFactoryBean;

    @Autowired
    @Qualifier("cronTriggerFactoryBean")
    CronTriggerFactoryBean cronTriggerFactoryBean;

}