package com.testvue.testvue.Service;

import com.testvue.testvue.enity.po.OrderMessage;

import java.util.List;

public interface OrderMessageService {
    List<OrderMessage> getOrderMessages();

    void deleteOrderMessageById(Long messageId);
}
