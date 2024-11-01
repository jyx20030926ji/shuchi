package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderPageDTO {

    private Integer page;

    private Integer pageSize;

    private Integer status;

    private Long userId;



}
