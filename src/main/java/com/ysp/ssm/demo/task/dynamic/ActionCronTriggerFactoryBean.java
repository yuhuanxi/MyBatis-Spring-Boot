package com.ysp.ssm.demo.task.dynamic;

import com.ysp.ssm.demo.model.City;
import com.ysp.ssm.demo.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Created by yuhuanxi on 16/8/30.
 */
@Component
public class ActionCronTriggerFactoryBean extends CronTriggerFactoryBean {

    @Autowired
    private ScheduledActionRunnerJobDetailFactory jobDetailFactory;

    @Value("${cron.pattern}")
    private String pattern;

    @Autowired
    private ICityService cityService;

    @Override
    public void afterPropertiesSet() throws ParseException {
        setCronExpression(pattern);
        System.out.println(jobDetailFactory.cityService);
        System.out.println(getCity());
        setJobDetail(jobDetailFactory.getObject());
        super.afterPropertiesSet();
    }

    City getCity() {
        return this.cityService.getById(56L);
    }
}
