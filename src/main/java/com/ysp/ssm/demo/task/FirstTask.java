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

    @Autowired
    @Qualifier("firstJobDetailFactoryBean")
//    @Resource(name = "firstJobDetailFactoryBean")
            JobDetailFactoryBean firstJobDetailFactoryBean;

    @Autowired
    @Qualifier("firstCronTriggerFactoryBean")
    CronTriggerFactoryBean firstCronTriggerFactoryBean;

    @Bean
    public JobDetailFactoryBean firstJobDetailFactoryBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("firstTask", new FirstTask());
        return CronTriggerAndJobDetailFactoryBeanGenerator.generatorJobDetail(FirstTaskJob.class, map, true, "firstTask");
    }

    @Bean
    public CronTriggerFactoryBean firstCronTriggerFactoryBean() {
        return CronTriggerAndJobDetailFactoryBeanGenerator.generatorCronTrigger(firstJobDetailFactoryBean.getObject(), 3000, "0/5 * * * * ?");
    }

    public void print() {
        System.out.println("doing first task job.......");
    }
}