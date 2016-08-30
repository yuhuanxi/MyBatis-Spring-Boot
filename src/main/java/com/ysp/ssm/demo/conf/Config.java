package com.ysp.ssm.demo.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuhuanxi on 16/8/18.
 */
@Configuration
@ConfigurationProperties(prefix = "cron.expression", locations = "classpath:/cron_expression.yml") // 该注解要写在类上,方能获取到数据
public class Config {

    private List<String> servers = new ArrayList<>();

    private String firstTask;

    /**
     * 读取配置文件中的 cron expressions
     *
     * @return
     */
    public String getFirstTask() {
        return this.firstTask;
    }

    public void setFirstTask(String firstTask) {
        this.firstTask = firstTask;
    }

    public List<String> getServers() {
        return this.servers;
    }
}
