package com.testvue.testvue.enity.po;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetail {

    private Long id;

    private Long  orderId;

    private String bookName;

    private String bookAuthor;

    private String imageUrl;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    private Integer bookNumber;

    private Double bookPrice;

    private Long bookId;

    private Long userId;

    private Long orderedUserId;

    private Integer orderStatus;


    private String cancelReason;

     private Long orderNumber;


}
