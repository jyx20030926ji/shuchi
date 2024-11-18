package com.testvue.testvue.enity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Document(indexName = "books")
public class Book {

    @Id
    private Long id;


    @Field(type = FieldType.Text)
    private String bookName;

    @Field(type = FieldType.Text)
    private String bookAuthor;

    private String bookISBN;

    private Double bookPrice;

    //1 为全新 //2 为二手图书

    private Integer bookStatus;

    private  Long  UserId;

    private LocalDateTime createTime;

    private LocalDateTime  updateTime;

    private String description;

    private String imageUrl;

    private Integer  stock;

    private Integer categoriesId;
}
