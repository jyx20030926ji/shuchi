package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageBookDTO {

    private Integer currentPage;


    private Integer pageSize;

    private  String bookName;

    private String bookAuthor;

    private Double bookStartPrice;

    private Double bookEndPrice;

    private Integer bookStatus;

    private Integer categoryId;

}
