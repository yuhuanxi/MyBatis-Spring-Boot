package com.ysp.ssm.demo.task;

import com.ysp.ssm.demo.model.City;
import com.ysp.ssm.demo.service.ICityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author: shipeng.yu
 * @time: 16/9/3-下午10:03
 * @version: 1.0
 * @description: 第一个 JOB
 */
public class FirstTaskJob extends QuartzJobBean {

    private static final Logger LOG = LogManager.getLogger(FirstTaskJob.class);

    FirstTask firstTask;

    public void setFirstTask(FirstTask firstTask) {
        this.firstTask = firstTask;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        String enable = (String) jobExecutionContext.getJobDetail().getJobDataMap().get("enable");

        // 启用job
        if ("enable".equals(enable)) {
            // 从 jobExecutionContext 获取到 service
            ICityService cityService = (ICityService) jobExecutionContext.getJobDetail().getJobDataMap().get("cityService");

            if (cityService != null) {
                City city = cityService.getById(56L);
                LOG.info("city:{}", city);
            }
            firstTask.print();
        }
    }

}
