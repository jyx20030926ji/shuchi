package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayOrderDTO {

    private Long orderId;

    private String paymentPassword;

    private Integer selectedPaymentMethod;
}
