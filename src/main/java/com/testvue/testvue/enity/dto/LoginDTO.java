package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    private String account;

    private String password;

    private String verificationCode;






}
