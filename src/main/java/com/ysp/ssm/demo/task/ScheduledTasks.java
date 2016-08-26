package com.ysp.ssm.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yuhuanxi on 16/8/26.
 */
@Component
public class ScheduledTasks {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //    @Scheduled(fixedRate = 5000)
//    "0 0 * * * *" = the top of every hour of every day.
//    "*/10 * * * * *" = every ten seconds.
//    "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//    "0 0/30 8-10 * * *" = 8:00, 8:30, 9:00, 9:30 and 10 o'clock every day.
//    "0 0 9-17 * * MON-FRI" = on the hour nine-to-five weekdays
//    "0 0 0 25 12 ?" = every Christmas Day at midnight

    /**
     * 文档
     * <p>
     * http://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html}
     */
    @Scheduled(cron = "*/10 * * * * *")
    public void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
