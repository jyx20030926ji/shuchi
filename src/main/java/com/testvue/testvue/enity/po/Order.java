package com.testvue.testvue.enity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    //1是微信支付  2是平台余额支付
    private Integer payWays;

    private Long id;
   //1 待支付 //2 待发货 //3 待收货 //4 已完成 //5 已取消
    private Integer orderStatus;

    //1 快递运输 2//飞机运输 3//海上货运
    private Integer deliverWays;

    private LocalDateTime deliverTime;
   //1 送货上门  2 快递站自取
    private Integer receipt;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime cancelTime;

    private String cancelReason;
    
    private LocalDateTime payTime;

    private Long userId;

    private Long orderNumber;

    private Long addressId;




}
