package com.ysp.ssm.demo.conf;

import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * Created by yuhuanxi on 16/8/26.
 */
@Component
public class ScheduleConfig {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorTask executorTask = new ScheduledExecutorTask();

        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setCronExpression("*/10 * * * * *");

        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setBeanName("factoryBean");
        schedulerFactoryBean.afterPropertiesSet();
    }
}
