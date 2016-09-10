package com.ysp.ssm.demo.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * @author: shipeng.yu
 * @time: 2016年09月10日 下午2:46
 * @version: 1.0
 * @since: 1.0
 * @description: 邮件工具类
 */
public class MailUtil {

    private static final Logger LOG = LoggerFactory.getLogger(MailUtil.class);

    /**
     * 发送简单邮件
     *
     * @throws Exception
     */
    public static void sendSimpleMail(JavaMailSender javaMailSender) throws Exception {
        if (javaMailSender != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("335747019@qq.com");
            message.setTo("adobe1874@126.com");
            message.setSubject("主题：简单邮件");
            message.setText("请登录后再操作");
            javaMailSender.send(message);
            LOG.info("send success...");
        } else {
            LOG.info("javaMailSender is null");
        }
    }
}
