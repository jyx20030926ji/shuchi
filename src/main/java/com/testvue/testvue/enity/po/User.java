package com.testvue.testvue.enity.po;


import cn.hutool.crypto.SecureUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {


    private Long id;

    private String name;
    private String gender;
    private Integer age ;
    private String address;
    private String city;
    private String account;
    private String password ;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer status;
     //最大余额2147483647

    private Double balance;

    private String avatarUrl;


    private  String email;



}
