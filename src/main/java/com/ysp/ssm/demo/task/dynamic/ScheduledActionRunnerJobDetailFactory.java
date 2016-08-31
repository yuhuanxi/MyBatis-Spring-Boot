package com.ysp.ssm.demo.task.dynamic;

import com.ysp.ssm.demo.service.ICityService;
import com.ysp.ssm.demo.task.FirstTask;
import com.ysp.ssm.demo.task.FirstTaskJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuhuanxi on 16/8/30.
 */
@Deprecated
@Component
public class ScheduledActionRunnerJobDetailFactory extends JobDetailFactoryBean {

    @Autowired
    public ICityService cityService;

    @Override
    public void afterPropertiesSet() {
        setJobClass(FirstTaskJob.class);    // 设置 FirstJob
        Map<String, Object> data = new HashMap<>();
        data.put("cityService", cityService);
        data.put("firstTask", new FirstTask());
        // durability 表示任务完成之后是否依然保留到数据库，默认false ,
        // false的话会报错 Jobs added with no trigger must be durable
        setDurability(true);

        setJobDataAsMap(data);
        super.afterPropertiesSet();
    }

}
