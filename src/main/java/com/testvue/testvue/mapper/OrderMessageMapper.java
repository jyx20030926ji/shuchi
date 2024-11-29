package com.testvue.testvue.mapper;

import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.enity.po.OrderMessage;
import com.testvue.testvue.menu.AopLogMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMessageMapper {

    @LogAnnotation(operation = "插入一条订单消息",aopLogMenu = AopLogMenu.INSERT)
    @TimeFiledAnnotation(AopLogMenu.INSERT)
    @Insert("insert into ordermessage( myself_id, other_id, content,update_time,create_time) VALUE (#{myselfId},#{otherId},#{content},#{updateTime},#{createTime})")
    void insert(OrderMessage orderMessage);

    @Select("select * from ordermessage where myself_id=#{myselfId}")
    List<OrderMessage> getOrderMessages(Long myselfId);

    @Delete("delete from ordermessage where id=#{messageId}")
    void deleteOrderMessageById(Long messageId);
}
