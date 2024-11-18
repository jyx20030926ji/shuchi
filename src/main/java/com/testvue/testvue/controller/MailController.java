package com.testvue.testvue.controller;


import com.testvue.testvue.Service.MailService;
import com.testvue.testvue.enity.po.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin/mails")
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发邮件到邮箱
     * 异步执行 提高效率
     *
     * @param mailTo
     * @return
     */
    @Async
    @GetMapping
    public Result sendMails(String mailTo)
    {
        try {

            String code = generateCaptcha();
            mailService.sendSimpleMail(mailTo, "验证码", code);
            stringRedisTemplate.opsForValue().set("code",code,60, TimeUnit.SECONDS);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Result.success();
    }


    private String generateCaptcha() {
        // 字母和数字的字符集
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // 使用 StringBuilder 来拼接生成的验证码
        StringBuilder captcha = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            // 随机选择一个字符，并拼接到验证码字符串
            captcha.append(chars.charAt(new Random().nextInt(chars.length())));
        }

        return captcha.toString();
    }
}
