/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ysp.ssm.demo.conf;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis扫描接口，使用的tk.mybatis.spring.mapper.MapperScannerConfigurer，如果你不使用通用Mapper，可以改为org.xxx...
 *
 * @author liuzh
 * @since 2015-12-19 14:46
 */
@Configuration
//TODO 注意，由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    // 开发环境数据源
    @Bean
    public MapperScannerConfigurer devMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("devSqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(DevRepository.class);
        mapperScannerConfigurer.setBasePackage("com.ysp.ssm.demo.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.ysp.ssm.demo.util.MyMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("cacheEnabled", "true");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    // 生产环境数据源
    @Bean
    public MapperScannerConfigurer prodMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("prodSqlSessionFactory");
        mapperScannerConfigurer.setAnnotationClass(ProdRepository.class);
        mapperScannerConfigurer.setBasePackage("com.ysp.ssm.demo.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.ysp.ssm.demo.util.MyMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("cacheEnabled", "true");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

    // 默认为开发环境数据源
    @Bean
    public MapperScannerConfigurer defaultMapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("devSqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.ysp.ssm.demo.mapper");
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.ysp.ssm.demo.util.MyMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        properties.setProperty("cacheEnabled", "true");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}
