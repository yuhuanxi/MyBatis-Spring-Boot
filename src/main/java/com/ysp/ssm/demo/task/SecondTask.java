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

@Configuration // 因为这里要注册多个 Bean ,用 @Configuration 注解,而不用 @Component
public class SecondTask {

    @Autowired
    @Qualifier("secondJobDetailFactoryBean")
//    @Resource(name = "secondJobDetailFactoryBean") // TODO 这里也不能使用 @Resource,具体原因待定
            JobDetailFactoryBean secondJobDetailFactoryBean;

    @Autowired
    @Qualifier("secondCronTriggerFactoryBean")
    CronTriggerFactoryBean secondCronTriggerFactoryBean;

    @Bean
    public JobDetailFactoryBean secondJobDetailFactoryBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("secondTask", new SecondTask());
        return CronTriggerAndJobDetailFactoryBeanGenerator.generatorJobDetail(SecondTaskJob.class, map, true, "secondTask");
    }

    @Bean
    public CronTriggerFactoryBean secondCronTriggerFactoryBean() {
        return CronTriggerAndJobDetailFactoryBeanGenerator.generatorCronTrigger(secondJobDetailFactoryBean.getObject(), 3000, "0/5 * * * * ?");
    }

    public void print() {
        System.out.println("doing second task job...");
    }
}