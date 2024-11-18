package com.testvue.testvue.enity.vo;

import com.testvue.testvue.enity.po.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetailVO {

    private Long addressId;

    private Double total;

    private List<OrderDetail> orderDetailList;

    private Integer payWays;


    //1 待支付 //2 待发货 //3 待收货 //4 已完成 //5 已取消
    private Integer orderStatus;

    //1 快递运输 2//飞机运输 3//海上货运
    private Integer deliverWays;

    private LocalDateTime deliverTime;
    //1 送货上门  2 快递站自取
    private Integer receipt;

    private LocalDateTime createTime;

    private LocalDateTime  updateTime;


    private LocalDateTime cancelTime;

    private String cancelReason;

    private LocalDateTime payTime;


    private Long orderNumber;

}
