package com.testvue.testvue.enity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friend {

    private Long id;

    private Long userId;

    private Long friendId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer status;

}
