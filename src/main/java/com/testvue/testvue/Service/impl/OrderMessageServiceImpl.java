package com.testvue.testvue.Service.impl;

import com.testvue.testvue.Service.OrderMessageService;

import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.enity.po.OrderMessage;
import com.testvue.testvue.mapper.OrderMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderMessageServiceImpl implements OrderMessageService {

    @Autowired
    private OrderMessageMapper orderMessageMapper;

    @Override
    public List<OrderMessage> getOrderMessages() {

        Long myselfId= BaseCont.get().longValue();
      return   orderMessageMapper.getOrderMessages(myselfId);
    }

    @Override
    public void deleteOrderMessageById(Long messageId) {
        orderMessageMapper.deleteOrderMessageById(messageId);

    }
}
