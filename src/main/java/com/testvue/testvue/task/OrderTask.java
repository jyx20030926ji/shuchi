package com.testvue.testvue.task;


import com.testvue.testvue.constant.StatusConstant;
import com.testvue.testvue.enity.po.Order;
import com.testvue.testvue.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {

    @Autowired

    private OrderMapper orderMapper;

    /**
     *  对待付款的订单1分钟执行一次 改变代付款状态为取消
     */
    @Scheduled(cron = "0 0/1 * * * ?" )
    public void updateOrder()
    {
      List<Order> orderList=orderMapper.getPendingPaymentOrder(LocalDateTime.now().minusMinutes(1));

      for(Order order:orderList)
      {

              order.setOrderStatus(StatusConstant.Five);
              orderMapper.updateOrderStatus(order);
      }
      log.info("定时任务已经完成-----------------");
    }

}
