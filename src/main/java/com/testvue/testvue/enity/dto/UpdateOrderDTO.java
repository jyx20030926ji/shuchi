package com.testvue.testvue.enity.dto;

import lombok.*;

@Data
@AllArgsConstructor

@NoArgsConstructor
@Builder
public class UpdateOrderDTO {

    private Long id;

    private String cancelReason;

    private Integer status;
}
