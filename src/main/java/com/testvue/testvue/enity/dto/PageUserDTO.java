package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageUserDTO {

    private Integer pageSize;

    private Integer page;

    private String account;

    private String name;

    private Integer status;

}
