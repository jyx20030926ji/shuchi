package com.testvue.testvue.controller.user;

import com.testvue.testvue.Service.OrderService;
import com.testvue.testvue.enity.dto.OrderDTO;
import com.testvue.testvue.enity.dto.OrderPageDTO;
import com.testvue.testvue.enity.dto.UpdateOrderDTO;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.vo.OrderDetailVO;
import com.testvue.testvue.enity.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
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
    public Result  insertOrder(@RequestBody OrderDTO orderDTO)
    {
        orderService.insertOrder(orderDTO);
        return Result.success();
    }

    /**
     * 分页查找订单信息
     * @param orderPageDTO
     * @return
     */
    @GetMapping
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

    @PutMapping
    public Result updateOrderStatus(@RequestBody UpdateOrderDTO updateOrderDTO)
    {

        orderService.updateOrderStatus(updateOrderDTO);
        return Result.success();

    }
}
