package com.testvue.testvue.controller.user;

import com.testvue.testvue.Service.OrderService;
import com.testvue.testvue.enity.dto.*;
import com.testvue.testvue.enity.po.OrderDetail;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.vo.AddressVO;
import com.testvue.testvue.enity.vo.OrderDetailVO;
import com.testvue.testvue.enity.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("user/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 插入一条新的订单
     * @param orderDTO
     * @return
     */
    @PostMapping
    public Result<Long>  insertOrder(@RequestBody OrderDTO orderDTO)
    {
        Long orderId = orderService.insertOrder(orderDTO);
        return Result.success(orderId);
    }

    /**
     * 分页查找订单信息
     * @param orderPageDTO
     * @return
     */
    @PostMapping("/page")
    Result<PageResult<OrderVO>> getOrder(@RequestBody OrderPageDTO orderPageDTO)
    {
     PageResult<OrderVO> orderVOPageResult= orderService.pagefindOrder(orderPageDTO);
     return Result.success(orderVOPageResult);
    }

    /**
     * 根据订单id查询订单详情
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<OrderDetailVO> getOrderDetail(@PathVariable Long id)
    {
      OrderDetailVO orderDetailVO=orderService.getOrderDetail(id);
      return Result.success(orderDetailVO);
    }

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result deleteOrdersByIds(@PathVariable List<Long> ids)
    {
        orderService.deletOrdersByIds(ids);
        return Result.success();
    }

    /**
     * 修改订单基本信息  //取消订单
     * @param updateOrderDTO
     * @return
     */
    @PutMapping
    public Result updateOrderStatus(@RequestBody UpdateOrderDTO updateOrderDTO)
    {

        orderService.updateOrderStatus(updateOrderDTO);
        return Result.success();

    }

    /**
     * 修改订单地址 两种修改形式 1 修改改地址的地址信息 用的是AddressController里的修改地址的接口 地址id不变 信息改变了
     * 2 这里写的是第二种 修改该订单的地址的id
     * @param orderId addressId
     * @return
     */
    @PutMapping("/{orderId}/{addressId}")
    public Result updateOrderAddressId(@PathVariable Long orderId,@PathVariable Long addressId)
    {
      orderService.updateOrderAddressId(orderId,addressId);
      return Result.success();
    }

    @GetMapping("/detail")
    public Result<List<OrderDetail>> getOrderDetailByUserId()
    {
      return Result.success(orderService.getOrderDeatilByUserId());
    }

    @PostMapping("/{orderDetailId}/{status}")
    public Result updateOrderDetailById(@PathVariable Long orderDetailId ,@PathVariable Integer status)
    {
        orderService.updateOrderDetailById(orderDetailId,status);
        return Result.success();
    }
    @PostMapping("/cancelOrderDetail")
    public Result cancelOrderDetail(@RequestBody OrderDetailCancelDTO orderDetailCancelDTO)
    {
        orderService.cancelOrderDetail(orderDetailCancelDTO);

        return Result.success();
    }

}
