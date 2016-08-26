package com.ysp.ssm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liuzh
 * @since 2015-12-12 18:22
 */
//@Controller
@RestController
@EnableWebMvc
@SpringBootApplication
@EnableScheduling
public class Application extends WebMvcConfigurerAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        LOG.info("application start...");
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/")
    String home() {
        return new String("Hello World");
    }
}
