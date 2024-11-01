package com.testvue.testvue.enity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ItemCart {


    private Long id;

    private Long  cartId;

    private String bookName;

    private String bookAuthor;

    private String imageUrl;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    private Integer bookNumber;

    private Double bookPrice;

    private Long bookId;


}
