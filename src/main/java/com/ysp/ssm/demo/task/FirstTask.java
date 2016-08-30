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

@Configuration  // 因为这里要注册多个 Bean ,用 @Configuration 注解
public class FirstTask {

    public void print() {
        System.out.println("doing first task job.......");
    }

    @Autowired
    @Qualifier("firstJobDetailFactoryBean")
//    @Resource(name = "firstJobDetailFactoryBean")
            JobDetailFactoryBean firstJobDetailFactoryBean;

    @Autowired
    @Qualifier("firstCronTriggerFactoryBean")
    CronTriggerFactoryBean firstCronTriggerFactoryBean;

    @Bean
    public CronTriggerFactoryBean firstCronTriggerFactoryBean() {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(firstJobDetailFactoryBean.getObject());
        trigger.setStartDelay(3000);
        // TODO 该表达式可从数据库或者配置文件中获取
        trigger.setCronExpression("0/5 * * * * ?");
        return trigger;
    }

    @Bean
    public JobDetailFactoryBean firstJobDetailFactoryBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(FirstTaskJob.class);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ftask", new FirstTask());
        jobDetail.setJobDataAsMap(map);

        jobDetail.setDurability(true);
        jobDetail.setName("fuckworld");
        return jobDetail;
    }
}