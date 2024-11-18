package com.testvue.testvue.enity.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {


    private Long id;

    private Long senderId;

    private Long receiverId;

    private String  content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer isReadMessage;

}
