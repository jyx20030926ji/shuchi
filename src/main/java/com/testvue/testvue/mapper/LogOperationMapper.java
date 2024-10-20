package com.testvue.testvue.mapper;

import com.testvue.testvue.enity.po.LogRecords;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogOperationMapper {


    void insertOperationLog(LogRecords logRecords);




}
