package com.testvue.testvue.Service;

import com.testvue.testvue.enity.dto.OrderDTO;
import com.testvue.testvue.enity.dto.OrderPageDTO;
import com.testvue.testvue.enity.dto.UpdateOrderDTO;
import com.testvue.testvue.enity.po.PageResult;

import com.testvue.testvue.enity.vo.OrderDetailVO;
import com.testvue.testvue.enity.vo.OrderVO;

import java.util.List;

public interface OrderService {
    Long insertOrder(OrderDTO orderDTO);

    PageResult<OrderVO> pagefindOrder(OrderPageDTO orderPageDTO);

    OrderDetailVO getOrderDetail(Long id);

    void deletOrdersByIds(List<Long> ids);

    void updateOrderStatus(UpdateOrderDTO updateOrderDTO);


    void updateOrderAddressId(Long orderId,Long addressId);
}
