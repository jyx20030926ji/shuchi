package com.testvue.testvue.mapper;

import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.enity.po.Message;
import com.testvue.testvue.menu.AopLogMenu;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.time.LocalDate;
import java.util.List;

@Mapper
public interface MessageMapper {


    @Select("select * from message where sender_id=#{senderId} and reciver_id=#{receiverId}")
    List<Message> getMessage(Message message);

    @LogAnnotation(operation = "插入一条新的信息",aopLogMenu = AopLogMenu.INSERT)
    @TimeFiledAnnotation(AopLogMenu.INSERT)
    @Insert("insert into message( sender_id, reciver_id, content, create_time,update_time,is_read_message) VALUES (#{senderId},#{receiverId},#{content},#{createTime},#{updateTime},#{isReadMessage})")
    void sendMessage(Message message);


    @LogAnnotation(operation = "批量修改消息状态",aopLogMenu = AopLogMenu.UPDATE)
    @TimeFiledAnnotation(AopLogMenu.UPDATE)
    void updateMessageStatus(List<Long> ids);


    @Delete("delete from message where create_time < #{now}")
    void orderDeleteMessage(LocalDate now);
}
