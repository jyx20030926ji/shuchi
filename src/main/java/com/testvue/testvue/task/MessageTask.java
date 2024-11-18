package com.testvue.testvue.task;

import com.testvue.testvue.mapper.MessageMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Slf4j
public class MessageTask {


    @Autowired
    private MessageMapper messageMapper;

    /**
     * 每天零点清楚除了昨天以外的聊天记录
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void messageTask()
    {
        messageMapper.orderDeleteMessage(LocalDate.now());
    }




}
