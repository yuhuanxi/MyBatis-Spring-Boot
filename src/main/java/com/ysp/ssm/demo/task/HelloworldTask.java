package com.ysp.ssm.demo.task;

/**
 * Created by yuhuanxi on 16/8/27.
 */

import org.springframework.stereotype.Component;

@Component
public class HelloworldTask {

    public void print() {
        System.out.println("Executing helloworld job...");
    }
}