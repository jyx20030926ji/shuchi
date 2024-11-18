package com.testvue.testvue.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送简单邮件
     * @param to 收件人邮箱地址
     * @param subject 邮件主题
     * @param text 邮件内容
     */
    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2736805640@qq.com");  // 发件人邮箱（使用你的 QQ 邮箱地址）
        message.setTo(to);  // 收件人邮箱
        message.setSubject(subject);  // 邮件主题
        message.setText(text);  // 邮件内容

        javaMailSender.send(message);  // 发送邮件
    }
}
