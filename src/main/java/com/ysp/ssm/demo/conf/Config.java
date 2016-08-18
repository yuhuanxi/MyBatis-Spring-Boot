package com.ysp.ssm.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuhuanxi on 16/8/18.
 */
@Configuration
// 读取 application.yml 中的my开头的配置文件
@ConfigurationProperties(prefix = "my")
public class Config {

    private List<String> servers = new ArrayList<String>();

    public List<String> getServers() {
        return servers;
    }
}
