package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {

    private String account;

    private String password;

    private String repeatPassword;

    private LocalDateTime createTime;

}
