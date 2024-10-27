package com.testvue.testvue.enity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDetailVO {

    private String account;

    private String address;

    private Integer  age;

    private String gender;

    private String city;

    private String imageUrl;

    private String description;



}
