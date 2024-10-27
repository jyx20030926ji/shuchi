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
public class Book {

    private Long id;


    private String bookName;

    private String bookAuthor;

    private String bookISBN;

    private Double bookPrice;

    private Integer bookStatus;

    private  Long  UserId;

    private LocalDateTime createTime;

    private LocalDateTime  updateTime;

    private String description;

    private String imageUrl;
}
