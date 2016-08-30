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

//@Component
@Configuration
public class FuckworldTask {

    public void print() {
        System.out.println("Executing fuckworld job.......");
    }

    @Autowired
    @Qualifier("fJobDetailFactoryBean")
    JobDetailFactoryBean fJobDetailFactoryBean;

    @Autowired
    @Qualifier("fCronTriggerFactoryBean")
    CronTriggerFactoryBean fCronTriggerFactoryBean;

    /**
     * 触发器 2
     *
     * @return
     */
    @Bean
    public CronTriggerFactoryBean fCronTriggerFactoryBean() {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(fJobDetailFactoryBean.getObject());
        trigger.setStartDelay(3000);
        // TODO 该表达式可从数据库或者配置文件中获取
        trigger.setCronExpression("0/5 * * * * ?");
        return trigger;
    }

    /**
     * jobDetail 2
     *
     * @return
     */
    @Bean
    public JobDetailFactoryBean fJobDetailFactoryBean() {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(FuckworldJob.class);

        // bring real ftask
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ftask", new FuckworldTask());
        jobDetail.setJobDataAsMap(map);

        jobDetail.setDurability(true);
        jobDetail.setName("fuckworld");
        return jobDetail;
    }
}