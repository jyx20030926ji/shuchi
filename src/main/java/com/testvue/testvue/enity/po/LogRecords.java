package com.testvue.testvue.enity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@TableName("operationlog")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogRecords {

    private Long id;


    private Long operationUserId;

    private String operationMethodName;

    private String operationBusinessType;

    private String operationName;

    private String operationParams;

    private LocalDateTime operationTime;



}
