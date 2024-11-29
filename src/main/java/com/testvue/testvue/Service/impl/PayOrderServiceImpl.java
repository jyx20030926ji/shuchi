package com.testvue.testvue.Service.impl;

import cn.hutool.crypto.SecureUtil;
import com.testvue.testvue.Service.OrderService;
import com.testvue.testvue.Service.PayOrderService;

import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.constant.StatusConstant;
import com.testvue.testvue.enity.dto.PayOrderDTO;
import com.testvue.testvue.enity.po.Order;
import com.testvue.testvue.enity.po.OrderDetail;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.OrderMapper;
import com.testvue.testvue.mapper.UserMapper;
import com.testvue.testvue.menu.CodeMessageMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

        List<OrderDetail> orderDetailList = orderMapper.getOrderDetail(id);

        Double noPay=0.00;

        for(OrderDetail orderDetail:orderDetailList) {
            if (orderDetail.getOrderStatus() == StatusConstant.ONE) {
                orderDetail.setOrderStatus(StatusConstant.TWO);
                orderMapper.updataOrderDetail(orderDetail);

            } else {
                noPay = orderDetail.getBookPrice() * orderDetail.getBookNumber() + noPay;
            }
        }
        total=total-noPay;

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

    @Override
    @Transactional
    public void payOrder(PayOrderDTO payOrderDTO) {

        Order order = orderMapper.getOrderById(payOrderDTO.getOrderId());

        if(order==null)
        {
            throw  new AccountNoExistException(CodeMessageMenu.ORDER_NOT_EXIST);
        }
        Long userId = BaseCont.get().longValue();

        User user = userMapper.selectById(userId);

        if(payOrderDTO.getSelectedPaymentMethod()==1) {

            if (user.getVxAccount() == null) {
                throw new AccountNoExistException(CodeMessageMenu.VX_ACCOUNT_NOTEXIST);
            }

            if (!user.getPassword().equals(SecureUtil.md5(payOrderDTO.getPaymentPassword()))) {
                throw new AccountNoExistException(CodeMessageMenu.PAY_PASSWORD_ERROR);
            }
        }
        else {
            if(user.getPayPassword()==null)
            {
                throw  new AccountNoExistException(CodeMessageMenu.PAY_PASSWORD_NOTEXIST);
            }
            if(!user.getPayPassword().equals(SecureUtil.md5(payOrderDTO.getPaymentPassword())))
            {
                throw  new AccountNoExistException(CodeMessageMenu.PASSWORD_BUSYS);
            }
            Double total = orderService.getOrderDetail(payOrderDTO.getOrderId()).getTotal();


            List<OrderDetail> orderDetailList = orderMapper.getOrderDetail(order.getId());

            Double noPay=0.00;

            for(OrderDetail orderDetail:orderDetailList)
            {
                if(orderDetail.getOrderStatus()==StatusConstant.ONE) {
                    orderDetail.setOrderStatus(StatusConstant.TWO);
                    orderMapper.updataOrderDetail(orderDetail);
                }else {
                    noPay=noPay+orderDetail.getBookNumber()*orderDetail.getBookPrice();
                }
            }
            total=total-noPay;

            if(user.getBalance()<total)
            {
                throw new AccountNoExistException(CodeMessageMenu.USER_BLANCE_NOT);
            }
            order.setPayWays(payOrderDTO.getSelectedPaymentMethod());
            order.setPayTime(LocalDateTime.now());
            order.setOrderStatus(StatusConstant.TWO);


            orderMapper.updateOrderStatus(order);
            user.setBalance(user.getBalance()-total);
            userMapper.update(user);
        }
    }
}
