package com.testvue.testvue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.testvue.testvue.enity.po.LogRecords;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface LogOperationMapper extends BaseMapper<LogRecords> {


    void insertOperationLog(LogRecords logRecords);




}
