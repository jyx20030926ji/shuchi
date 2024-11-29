package com.testvue.testvue.Service;

import com.testvue.testvue.enity.dto.PayOrderDTO;

public interface PayOrderService {
    void payOrders(Long id);

    void payOrder(PayOrderDTO payOrderDTO);
}
