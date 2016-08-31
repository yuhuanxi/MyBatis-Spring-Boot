package com.ysp.ssm.demo.task;

/**
 * Created by yuhuanxi on 16/8/27.
 */

import com.ysp.ssm.demo.service.ICityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration  // 因为这里要注册多个 Bean ,用 @Configuration 注解
public class FirstTask {

    private static final Logger LOG = LogManager.getLogger(FirstTask.class);

    @Autowired
    @Qualifier("cityService")
    public ICityService cityService;

    @Value("${cron.pattern}")   // 从配置文件中获取 cron 表达式
    private String pattern;

    @Autowired
    @Qualifier("firstJobDetailFactoryBean")
    JobDetailFactoryBean firstJobDetailFactoryBean;

    @Autowired
    @Qualifier("firstCronTriggerFactoryBean")
    CronTriggerFactoryBean firstCronTriggerFactoryBean;

    @Bean
    public JobDetailFactoryBean firstJobDetailFactoryBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("firstTask", new FirstTask());
        map.put("cityService", cityService);
        return CronTriggerAndJobDetailFactoryBeanGenerator.generatorJobDetail(FirstTaskJob.class, map, true, "firstTask");
    }

    @Bean
    public CronTriggerFactoryBean firstCronTriggerFactoryBean() {
        return CronTriggerAndJobDetailFactoryBeanGenerator.generatorCronTrigger(firstJobDetailFactoryBean.getObject(), 3000, pattern);
    }

    public void print() {
        System.out.println("doing first task job.......");
    }
}