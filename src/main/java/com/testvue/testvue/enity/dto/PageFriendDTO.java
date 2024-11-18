package com.testvue.testvue.enity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageFriendDTO {


    private Long userId;

    private Integer page;

    private Integer pageSize;

    private Integer status;





}
