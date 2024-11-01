package com.testvue.testvue.enity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressVO {

    private String name;

    private String phone;

    private String basicAddress;

    private String detailAddress;

    private Integer status;

}
