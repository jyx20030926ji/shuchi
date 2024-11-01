package com.testvue.testvue.Service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.testvue.testvue.Service.OrderService;

import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.constant.StatusConstant;
import com.testvue.testvue.enity.dto.OrderDTO;
import com.testvue.testvue.enity.dto.OrderPageDTO;
import com.testvue.testvue.enity.dto.UpdateOrderDTO;
import com.testvue.testvue.enity.po.*;
import com.testvue.testvue.enity.vo.AddressVO;
import com.testvue.testvue.enity.vo.OrderDetailVO;
import com.testvue.testvue.enity.vo.OrderVO;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.AddressMapper;
import com.testvue.testvue.mapper.CartMapper;
import com.testvue.testvue.mapper.OrderMapper;
import com.testvue.testvue.menu.CodeMessageMenu;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {



    @Autowired
    private OrderMapper  orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 插入一条新的订单
     * @param orderDTO
     */
    @Override
    public void insertOrder(OrderDTO orderDTO) {

        Long userId=BaseCont.get().longValue();

        Order order=new Order();

        BeanUtils.copyProperties(orderDTO,order);

        order.setOrderStatus(StatusConstant.ONE);

        order.setOrderNumber(orderMapper.getlatestOrderNumber());

        order.setAddressId(addressMapper.selectDefault(userId).getId());

        order.setUserId(userId);

        order.setDeliverTime(LocalDateTime.now().plusDays(2));

        orderMapper.insertOrder(order);

        Cart cart = cartMapper.selectCartByUserId(userId);

        if(cart==null)
        {
            throw  new AccountNoExistException(CodeMessageMenu.CART_NOT_EXIST);
        }

        List<ItemCart> itemCarts = cartMapper.selectCartListById(cart.getId());

        List<OrderDetail> orderDetails = BeanUtil.copyToList(itemCarts, OrderDetail.class);

        for(OrderDetail orderDetail:orderDetails)
        {
            orderDetail.setOrderId(order.getId());
        }

        orderMapper.insetOrderDetail(orderDetails);
        cartMapper.deleteByCartId(cart.getId());
        cartMapper.deleteCartByUserId(userId);

    }

    /**
     * 根据订单状态分页查询全部订单
     * @param orderPageDTO
     * @return
     */
    @Override
    public PageResult<OrderVO> pagefindOrder(OrderPageDTO orderPageDTO) {

        orderPageDTO.setUserId(BaseCont.get().longValue());

        orderPageDTO.setPage((orderPageDTO.getPage()-1)*orderPageDTO.getPageSize());

       Long totalRecords=orderMapper.findOrderTotal(orderPageDTO);

        List<Order> orders =orderMapper.pagefindOrder(orderPageDTO);

        List<OrderVO> orderVOS=new ArrayList<>();

       List<Long> orderIds = orders.stream().map(Order::getId).collect(Collectors.toList());

       for(Long id:orderIds)
       {
            List<OrderDetail> orderDetails=orderMapper.getOrderDetail(id);
             Integer number=0;
             Double total=0.00;
             List<String> imageUrls=new ArrayList<>();
          for(OrderDetail orderDetail:orderDetails)
          {
             number=number+orderDetail.getBookNumber();
             total=total+orderDetail.getBookPrice()*orderDetail.getBookNumber();
            imageUrls.add(orderDetail.getImageUrl());

          }
          orderVOS.add(new OrderVO(imageUrls,number,total));

       }
       return new PageResult<>(orderVOS,totalRecords);
    }

    /**
     * 根据订单id查询订单详情
     * @param id
     * @return
     */
    @Override
    public OrderDetailVO getOrderDetail(Long id) {

        List<OrderDetail> orderDetailList = orderMapper.getOrderDetail(id);

        Address address=orderMapper.getAddressByOrderId(id);

       Order order=orderMapper.getOrderById(id);

        AddressVO addressVO=new AddressVO();

       BeanUtils.copyProperties(address,addressVO);

        Double total=0.00;

        for(OrderDetail orderDetail:orderDetailList)
        {
            total=total+orderDetail.getBookPrice()*orderDetail.getBookNumber();
        }
        OrderDetailVO orderDetailVO = OrderDetailVO.builder().orderDetailList(orderDetailList)
                .addressVO(addressVO)
                .createTime(order.getCreateTime())
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                .total(total)
                .cancelReason(order.getCancelReason())
                .cancelTime(order.getCancelTime())
                .deliverTime(order.getDeliverTime())
                .deliverWays(order.getDeliverWays())
                .payWays(order.getPayWays())
                .receipt(order.getReceipt())
                .payTime(order.getPayTime())
                .updateTime(order.getUpdateTime())
                .build();

           return orderDetailVO;

    }

    /** 只有订单被取消或者完成才能删除 4是完成 5是取消
     * 批量删除订单
     * @param ids
     */
    @Override
    @Transactional //删除回滚
    public void deletOrdersByIds(List<Long> ids) {

        for(Long id:ids)
        {
            Order order = orderMapper.getOrderById(id);
            if(order.getOrderStatus()!=StatusConstant.FOUR && order.getOrderStatus()!=StatusConstant.Five)
            {
                throw  new AccountNoExistException(CodeMessageMenu.ORDER_STATUS_NO_CAN_DELETE);
            }
        }

        orderMapper.deleteOrderByIds(ids);
    }

    /**
     * 用户修改订单状态  //用户只能取消订单
     * @param updateOrderDTO
     */
    @Override
    public void updateOrderStatus(UpdateOrderDTO updateOrderDTO) {

           //根据订单id判断订单状态 处于代付款或待发货才能取消 否则需要与店家协商

        Order order = orderMapper.getOrderById(updateOrderDTO.getId());

        if(order.getOrderStatus()!=StatusConstant.ONE && order.getOrderStatus()!=StatusConstant.TWO)
        {
            throw  new AccountNoExistException(CodeMessageMenu.ORDER_STATUS_NO_CAN_DELETE);
        }

        order.setOrderStatus(updateOrderDTO.getStatus());
        order.setCancelReason(updateOrderDTO.getCancelReason());
        order.setCancelTime(LocalDateTime.now());

        orderMapper.updateOrderStatus(order);

    }


}
