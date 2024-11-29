package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailCancelDTO {


   private Long orderDetailId;

   private String cancelReason;

   private Integer status;


}
