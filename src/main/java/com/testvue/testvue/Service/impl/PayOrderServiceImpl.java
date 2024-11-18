package com.testvue.testvue.Service.impl;

import com.testvue.testvue.Service.OrderService;
import com.testvue.testvue.Service.PayOrderService;

import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.constant.StatusConstant;
import com.testvue.testvue.enity.po.Order;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.OrderMapper;
import com.testvue.testvue.mapper.UserMapper;
import com.testvue.testvue.menu.CodeMessageMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PayOrderServiceImpl implements PayOrderService {


    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserMapper userMapper;

    /** author jyx
     * 根据订单id实现支付功能
     * @param id
     */
    @Override
    public void payOrders(Long id) {

        Order order = orderMapper.getOrderById(id);


        if(order==null)
        {
            throw  new AccountNoExistException(CodeMessageMenu.ORDER_NOT_EXIST);
        }
        if(order.getOrderStatus()!=StatusConstant.ONE.intValue())
        {
            throw  new AccountNoExistException(CodeMessageMenu.ORDER_ALREADY_PAYED);
        }

        Integer payWays = order.getPayWays();

        if(payWays==StatusConstant.ONE.intValue())
        {
            throw  new AccountNoExistException(404,"暂不支持该功能");
        }

        User user = userMapper.selectById(BaseCont.get().longValue());

        Double balance = user.getBalance();


        Double total = orderService.getOrderDetail(id).getTotal();

        if(total>balance)
        {
            throw  new AccountNoExistException(404,"余额不足请重新支付");
        }
        user.setBalance(balance-total);
        userMapper.update(user);

        order.setOrderStatus(StatusConstant.TWO);
        order.setPayTime(LocalDateTime.now());
        orderMapper.updateOrderStatus(order);

    }
}
