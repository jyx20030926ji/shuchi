package com.testvue.testvue.enity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MessageSendDTO {

    private String content;

    private Long receiverId;

}
