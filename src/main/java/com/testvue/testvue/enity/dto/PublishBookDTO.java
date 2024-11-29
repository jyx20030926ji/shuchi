package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublishBookDTO {

    private Long id;

    private String bookName;

    private String description;

    private String bookISBN;

    private Double bookPrice;

    private String bookAuthor;

    private Integer bookStatus;

    private String imageUrl;

    private Integer stock;


}
