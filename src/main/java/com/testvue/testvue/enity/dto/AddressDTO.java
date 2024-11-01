package com.testvue.testvue.enity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private Long id;

    private String name;

    private String phone;

    private Integer status;

    private String basicAddress;

    private String detailAddress;


}
