package com.ysp.ssm.demo.cache;

import com.google.code.ssm.config.DefaultAddressProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: shipeng.yu
 * @time: 2016年09月04日 下午5:32
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
@Configuration
@ConfigurationProperties(locations = "classpath:/memcached.yml", prefix = "memcached")
public class MemcachedManage {

    private String address;

    private int port;

    @Bean(name = "defaultAddressProvider")
    public DefaultAddressProvider defaultAddressProvider() {
        DefaultAddressProvider defaultAddressProvider = new DefaultAddressProvider();
        defaultAddressProvider.setAddress(address + ":" + port);
        return defaultAddressProvider;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
