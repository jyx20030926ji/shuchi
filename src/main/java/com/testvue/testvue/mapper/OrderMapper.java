package com.testvue.testvue.mapper;

import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.enity.dto.OrderPageDTO;
import com.testvue.testvue.enity.po.Address;
import com.testvue.testvue.enity.po.Order;
import com.testvue.testvue.enity.po.OrderDetail;
import com.testvue.testvue.menu.AopLogMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    @LogAnnotation(operation = "新增一条订单",aopLogMenu = AopLogMenu.INSERT)
    @TimeFiledAnnotation(AopLogMenu.INSERT)
    void insertOrder(Order order);


    @LogAnnotation(operation = "新增一条订单详情",aopLogMenu = AopLogMenu.INSERT)
    @TimeFiledAnnotation(AopLogMenu.INSERT)
    void insetOrderDetail(List<OrderDetail> orderDetails);

    List<Order> pagefindOrder(OrderPageDTO orderPageDTO);

    Long findOrderTotal(OrderPageDTO orderPageDTO);

    @Select("select * from order_detail where order_id=#{orderId}")
    List<OrderDetail> getOrderDetail(Long orderId);

    @Select("select a.* from address a,`order` o where o.address_id=a.id and o.id=#{id} ")
    Address getAddressByOrderId(Long id);

    @Select("select * from `order` where id=#{id}")
    Order getOrderById(Long id);

    @Select("select order_number from `order` order by order_number desc limit 1")
    Long getlatestOrderNumber();

    void deleteOrderByIds(List<Long> ids);


    @LogAnnotation(operation = "更改订单信息",aopLogMenu = AopLogMenu.UPDATE)
    @TimeFiledAnnotation(AopLogMenu.UPDATE)
    void updateOrderStatus(Order order);


    @Select("select * from `order`where order_status=1 and create_time < #{localDateTime}")
    List<Order> getPendingPaymentOrder(LocalDateTime localDateTime);

    @LogAnnotation(operation = "删除订单详情",aopLogMenu = AopLogMenu.DELETE)
    @Delete("delete from order_detail where order_id=#{id}")
    void deleteOrderDetails(Long id);


    @Select("select * from order_detail where ordered_user_id=#{id}")
    List<OrderDetail> getOrderDetailByUserId(Long id);


    @TimeFiledAnnotation(AopLogMenu.UPDATE)
    void updataOrderDetail(OrderDetail orderDetail);

    @Select("select * from order_detail where id=#{orderDetailId}")
    OrderDetail getOrderDetailById(Long orderDetailId);
}
