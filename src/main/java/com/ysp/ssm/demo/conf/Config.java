package com.ysp.ssm.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuhuanxi on 16/8/18.
 */
@Configuration
@ConfigurationProperties(prefix = "my") // 该注解要写在类上,方能获取到数据
public class Config {

    private List<String> servers = new ArrayList<>();

    // 读取 application.yml 中的my开头的配置文件
    public List<String> getServers() {
        return this.servers;
    }

//    @Bean(name = "prodDataSource")
//    @Qualifier("prodDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
//    public DataSource prodDataSource() throws SQLException {
//        return DataSourceBuilder.create().build();
//    }
}
