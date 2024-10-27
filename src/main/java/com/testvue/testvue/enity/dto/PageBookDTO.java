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

    private Integer pageSize;

    private Integer currentPage;

    private Integer bookStatus;

    private String bookName;

    private String bookAuthor;

    private String bookStartPrice;

    private String bookEndPrice;


}
