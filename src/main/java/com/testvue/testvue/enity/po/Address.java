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
public class Address {
    private Long id;

    private String basicAddress;

    private String  detailAddress;

    private String name;

    private String phone;

    private Integer status;

    private Long userId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
