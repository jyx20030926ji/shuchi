package com.testvue.testvue.mapper;

import com.github.pagehelper.Page;
import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;


import com.testvue.testvue.enity.dto.PageUserDTO;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.menu.AopLogMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface UserMapper {

    @LogAnnotation(operation = "查询全部用户", aopLogMenu = AopLogMenu.LIST)
    @Select("select * from users ")
    List<User> userlist();


    @LogAnnotation(operation = "新增用户", aopLogMenu = AopLogMenu.INSERT)
    @Insert("insert into users(name, age, gender) VALUE(#{name},#{age},#{gender})")
    void adduser(User user);


    @LogAnnotation(operation = "删除用户",aopLogMenu = AopLogMenu.DELETE)
    @Delete("delete from users where id=#{id}")
    void deleteById(Integer id);


    @LogAnnotation(operation = "根据用户账号查询用户信息",aopLogMenu = AopLogMenu.LIST)
    @Select("select * from users where account=#{account}")
    User selectByAccount(String account);


    @TimeFiledAnnotation(value = AopLogMenu.INSERT)
    @LogAnnotation(operation = "用户注册",aopLogMenu = AopLogMenu.INSERT)
    @Insert("insert into users(account,password,create_time,update_time) VALUES(#{account},#{password},#{createTime},#{updateTime}) ")
    void inserBasicInformation(User user);


    @LogAnnotation(operation = "修改用户信息",aopLogMenu =AopLogMenu.UPDATE)
    @TimeFiledAnnotation(value = AopLogMenu.UPDATE)
    void update(User user);


    Page<User> pagefind(PageUserDTO pageUserDTO);
}



