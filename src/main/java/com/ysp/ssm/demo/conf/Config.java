package com.ysp.ssm.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shipeng.yu
 * @Time: 2016/09/03-9:58
 * @Version: 1.0
 * @Description:
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
