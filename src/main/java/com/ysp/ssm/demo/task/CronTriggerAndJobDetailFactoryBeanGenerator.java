package com.ysp.ssm.demo.task;

import com.ysp.ssm.demo.service.ICityService;
import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import java.util.Map;

/**
 * Created by yuhuanxi on 16/8/30.
 */
public class CronTriggerAndJobDetailFactoryBeanGenerator {

    /**
     * 生成触发器
     *
     * @param jobDetail
     * @param startDelay
     * @param cronExpression
     * @return
     */
    public static CronTriggerFactoryBean generatorCronTrigger(JobDetail jobDetail, long startDelay, String cronExpression) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(jobDetail);
        trigger.setStartDelay(startDelay);
        trigger.setCronExpression(cronExpression);
        return trigger;
    }

    /**
     * 生成 jobDetail
     *
     * @param jobClass
     * @param jobDataAsMap
     * @param durability
     * @param jobName
     * @return
     */
    public static JobDetailFactoryBean generatorJobDetail(Class<?> jobClass, Map<String, ?> jobDataAsMap, Boolean durability, String jobName) {
        JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
        jobDetail.setJobClass(jobClass);
        jobDetail.setJobDataAsMap(jobDataAsMap);
        jobDetail.setDurability(durability);
        jobDetail.setName(jobName);
        return jobDetail;
    }

}
