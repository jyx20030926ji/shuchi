package com.testvue.testvue.enity.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO {

    private Long id;

    private List<String> imageUrls;

    private Integer number;

    private Double total;

    private Long OrderNumber;

    private Integer orderStatus;

    private LocalDateTime createTime;
}
