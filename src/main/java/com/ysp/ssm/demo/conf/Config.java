package com.ysp.ssm.demo.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuhuanxi on 16/8/18.
 */
@Configuration
public class Config {

    private List<String> servers = new ArrayList<String>();

    // 读取 application.yml 中的my开头的配置文件
    @ConfigurationProperties(prefix = "my")
    public List<String> getServers() {
        return servers;
    }

//    @Bean(name = "prodDataSource")
//    @Qualifier("prodDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.primary")
//    public DataSource prodDataSource() throws SQLException {
//        return DataSourceBuilder.create().build();
//    }
}
