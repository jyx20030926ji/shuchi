package com.testvue.testvue.enity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@TableName("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {


    private Long id;

    private String name;
    private String gender;
    private Integer age ;
    private String address;
    private String city;
    private String account;
    private String password;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;




}
