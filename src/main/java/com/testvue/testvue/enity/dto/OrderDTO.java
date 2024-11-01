package com.testvue.testvue.enity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class OrderDTO {

    //1 快递运输 2//飞机运输 3//海上货运
    private Integer deliverWays;


    //1 送货上门  2 快递站自取
    private Integer receipt;
    //1是微信支付  2是平台余额支付
    private Integer payWays;
}
