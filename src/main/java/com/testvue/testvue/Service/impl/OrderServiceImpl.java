package com.testvue.testvue.Service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.mustachejava.Code;
import com.testvue.testvue.Service.OrderService;

import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.constant.StatusConstant;
import com.testvue.testvue.enity.dto.OrderDTO;
import com.testvue.testvue.enity.dto.OrderDetailCancelDTO;
import com.testvue.testvue.enity.dto.OrderPageDTO;
import com.testvue.testvue.enity.dto.UpdateOrderDTO;
import com.testvue.testvue.enity.po.*;
import com.testvue.testvue.enity.vo.OrderDetailVO;
import com.testvue.testvue.enity.vo.OrderVO;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.*;
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

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMessageMapper orderMessageMapper;

    /**
     * 插入一条新的订单  判断插入的订单详情数量是否大于库存 若大于则插入订单数据后回滚
     * @param orderDTO
     */
    @Override
    @Transactional
    public Long insertOrder(OrderDTO orderDTO) {

        Long userId=BaseCont.get().longValue();

        Order order=new Order();

        BeanUtils.copyProperties(orderDTO,order);

        order.setOrderStatus(StatusConstant.ONE);

        Long orderNumber = orderMapper.getlatestOrderNumber();

        User my = userMapper.selectById(BaseCont.get().longValue());

        if(orderNumber==null)
        {
            order.setOrderNumber(199777L);
        }
        else {

        order.setOrderNumber(orderNumber+1); }

        try {
            order.setAddressId(addressMapper.selectDefault(userId).getId());
         }
       catch (Exception e)
       {
           throw new AccountNoExistException(CodeMessageMenu.DEFAULT_ADDRESS_NOT_EXIST);
       }

        order.setUserId(userId);

        order.setDeliverTime(LocalDateTime.now().plusDays(2));

        orderMapper.insertOrder(order);

        Cart cart = cartMapper.selectCartByUserId(userId);

        if(cart==null)
        {
            throw  new AccountNoExistException(CodeMessageMenu.CART_NOT_EXIST);
        }

        List<ItemCart> itemCarts = cartMapper.selectCartListById(cart.getId());

        for(ItemCart itemCart:itemCarts)
        {
            Book book = bookMapper.selectById(itemCart.getBookId());
            User user=userMapper.getUserByBookId(book.getId());

            if(book.getStock()<itemCart.getBookNumber())
            {
                throw new AccountNoExistException(404,itemCart.getBookName()+"库存不足请重试");
            }
             book.setStock(book.getStock()-itemCart.getBookNumber());
             bookMapper.updateById(book);
             OrderMessage orderMessage=new OrderMessage();
             orderMessage.setMyselfId(user.getId());
             orderMessage.setOtherId(my.getId());

              String content="用户"+my.getName()+"购买了你的图书"+book.getBookName();
              orderMessage.setContent(content);
              orderMessageMapper.insert(orderMessage);
        }

        List<OrderDetail> orderDetails = BeanUtil.copyToList(itemCarts, OrderDetail.class);

        for(OrderDetail orderDetail:orderDetails)
        {
            orderDetail.setOrderStatus(StatusConstant.ONE);
            orderDetail.setOrderId(order.getId());
            orderDetail.setUserId(BaseCont.get().longValue());

            orderDetail.setOrderNumber(order.getOrderNumber());
            User user = userMapper.getUserByBookId(orderDetail.getBookId());
            orderDetail.setOrderedUserId(user.getId());
        }
        orderMapper.insetOrderDetail(orderDetails);

        cartMapper.deleteByCartId(cart.getId());
        cartMapper.deleteCartByUserId(userId);
        return order.getId();

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
           Order order = orderMapper.getOrderById(id);

           orderVOS.add(new OrderVO(id,imageUrls,number,total,order.getOrderNumber(),order.getOrderStatus(),order.getUpdateTime()));

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





        Double total=0.00;

        for(OrderDetail orderDetail:orderDetailList)
        {
            total=total+orderDetail.getBookPrice()*orderDetail.getBookNumber();
        }
        OrderDetailVO orderDetailVO = OrderDetailVO.builder().orderDetailList(orderDetailList)
                .addressId(address.getId())
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
            if(order.getOrderStatus()!=StatusConstant.FOUR.intValue() && order.getOrderStatus()!=StatusConstant.Five.intValue())
            {
                throw  new AccountNoExistException(CodeMessageMenu.ORDER_STATUS_NO_CAN_DELETE);
            }
            orderMapper.deleteOrderDetails(order.getId());
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

        List<OrderDetail> orderDetailList = orderMapper.getOrderDetail(order.getId());

        Double total=0.00;

        for(OrderDetail orderDetail:orderDetailList)
        {
            total=total+orderDetail.getBookNumber()*orderDetail.getBookPrice();
            orderDetail.setOrderStatus(updateOrderDTO.getStatus());
            orderDetail.setCancelReason(updateOrderDTO.getCancelReason());
            orderMapper.updataOrderDetail(orderDetail);
        }

        User user = userMapper.selectById(BaseCont.get().longValue());

        user.setBalance(user.getBalance()+total);

        userMapper.update(user);

        orderMapper.updateOrderStatus(order);

    }

    /**
     * 修改订单地址信息  修改订单的地址的id
     * @param orderId
     * @param addressId
     */

    @Override
    public void updateOrderAddressId(Long orderId,Long addressId) {
        Order order=new Order();

        order.setId(orderId);
        order.setAddressId(addressId);

        orderMapper.updateOrderStatus(order);

    }

    @Override
    public List<OrderDetail> getOrderDeatilByUserId() {
      return  orderMapper.getOrderDetailByUserId(BaseCont.get().longValue());
    }

    @Override
    public void updateOrderDetailById(Long orderDetailId, Integer status) {

       OrderDetail orderDetail=orderMapper.getOrderDetailById(orderDetailId);

       if(orderDetail==null)
       {
           throw new AccountNoExistException(CodeMessageMenu.ORDER_DETAIL_NOT_EXIST);
       }
       orderDetail.setOrderStatus(status);

       orderMapper.updataOrderDetail(orderDetail);

        List<OrderDetail> orderDetailList = orderMapper.getOrderDetail(orderDetail.getOrderId());

        int size = orderDetailList.size();

        int statusSize=0;

        for(OrderDetail orderDetail1:orderDetailList)
        {
            if(orderDetail1.getOrderStatus()==status)
            {
                statusSize=statusSize+1;
            }
        }
        if(statusSize==size)
        {
            Order order=orderMapper.getOrderById(orderDetail.getOrderId());
            order.setOrderStatus(status);
            orderMapper.updateOrderStatus(order);
        }
    }

    @Override
    public void cancelOrderDetail(OrderDetailCancelDTO orderDetailCancelDTO) {

        OrderDetail orderDetail = orderMapper.getOrderDetailById(orderDetailCancelDTO.getOrderDetailId());

        if(orderDetail==null)
        {
            throw new AccountNoExistException(CodeMessageMenu.ORDER_DETAIL_NOT_EXIST);
        }
        orderDetail.setOrderStatus(orderDetailCancelDTO.getStatus());
        orderDetail.setCancelReason(orderDetailCancelDTO.getCancelReason());
        orderMapper.updataOrderDetail(orderDetail);

        List<OrderDetail> orderDetailList = orderMapper.getOrderDetail(orderDetail.getOrderId());

        int size = orderDetailList.size();

        int statusSize=0;

        for(OrderDetail orderDetail1:orderDetailList)
        {
            if(orderDetail1.getOrderStatus()==orderDetailCancelDTO.getStatus())
            {
                statusSize=statusSize+1;
            }
        }

        if(size==statusSize)
        {
            Order order = orderMapper.getOrderById(orderDetail.getOrderId());
            order.setOrderStatus(orderDetailCancelDTO.getStatus());
            orderMapper.updateOrderStatus(order);
        }


    }

}
