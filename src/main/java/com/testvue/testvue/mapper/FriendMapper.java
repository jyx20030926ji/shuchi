package com.testvue.testvue.mapper;

import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.enity.dto.PageFriendDTO;
import com.testvue.testvue.enity.po.Friend;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.menu.AopLogMenu;
import lombok.extern.java.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface FriendMapper {



    List<User> findFriends(PageFriendDTO pageFriendDTO);



    @LogAnnotation(operation = "请求添加用户",aopLogMenu = AopLogMenu.INSERT)
    @TimeFiledAnnotation(AopLogMenu.INSERT)
    @Insert("insert into friend( user_id, friend_id, create_time,update_time,status) VALUES (#{userId},#{friendId},#{createTime},#{updateTime},#{status})")
    void insertFriend(Friend friend);


    @LogAnnotation(operation = "改变用户间的状态",aopLogMenu = AopLogMenu.UPDATE)
    @TimeFiledAnnotation(AopLogMenu.UPDATE)
    @Update("UPDATE friend set status=#{status} where user_id=#{userId} and  friend_id=#{friendId}")
    void updateFriendStatus(Friend friend);

    @LogAnnotation(operation = "查询用户请求信息",aopLogMenu = AopLogMenu.OTHER)
    List<User> findRequestFriends(PageFriendDTO pageFriendDTO);

    @Select("select * from friend where user_id=#{userId}")
    List<Friend> findFriendsByUserId(Long userId);
}
