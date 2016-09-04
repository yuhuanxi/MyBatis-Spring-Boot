package com.ysp.ssm.demo.cache;

import com.google.code.ssm.CacheFactory;
import com.google.code.ssm.config.DefaultAddressProvider;
import com.google.code.ssm.providers.CacheConfiguration;
import com.google.code.ssm.providers.xmemcached.MemcacheClientFactoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

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

    private static final Logger LOG = LogManager.getLogger(MemcachedManage.class);

    private String address;

    private int port;

    private String add = "localhost:11211";

//    @Autowired
//    public DefaultAddressProvider defaultAddressProvider;
//
//    @Autowired
//    public MemcacheClientFactoryImpl memcacheClientFactory;
//
//    @Autowired
//    public CacheConfiguration cacheConfiguration;

    @Autowired
    @Qualifier("cacheFactory")
    public CacheFactory cacheFactory;

    @Bean(name = "cacheFactory")
    public CacheFactory cacheFactory() throws Exception {
        CacheFactory cacheFactory = new CacheFactory();
        cacheFactory.setCacheClientFactory(memcacheClientFactory());
        cacheFactory.setConfiguration(cacheConfiguration());
        cacheFactory.setAddressProvider(defaultAddressProvider());
        cacheFactory.setCacheName("dev");  // 名称必填
        cacheFactory.afterPropertiesSet();
        return cacheFactory;
    }

    //    @Bean(name = "defaultAddressProvider")
    public DefaultAddressProvider defaultAddressProvider() {
        DefaultAddressProvider defaultAddressProvider = new DefaultAddressProvider();
        defaultAddressProvider.setAddress(add);
        return defaultAddressProvider;
    }

    //    @Bean(name = "memcacheClientFactory")
    public MemcacheClientFactoryImpl memcacheClientFactory() {
        MemcacheClientFactoryImpl memcacheClientFactory = new MemcacheClientFactoryImpl();
        try {
            memcacheClientFactory.create(defaultAddressProvider().getAddresses(), cacheConfiguration());
            return memcacheClientFactory;
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    //    @Bean(name = "cacheConfiguration")
    public CacheConfiguration cacheConfiguration() {
        CacheConfiguration cacheConfiguration = new CacheConfiguration();
        cacheConfiguration.setConsistentHashing(true);
        return cacheConfiguration;
    }

    public static void main(String[] args) {


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
