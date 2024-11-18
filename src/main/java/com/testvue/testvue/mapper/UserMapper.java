package com.testvue.testvue.mapper;



import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;


import com.testvue.testvue.enity.dto.PageUserDTO;
import com.testvue.testvue.enity.dto.UserRegisterDTO;
import com.testvue.testvue.enity.po.User;
import com.testvue.testvue.menu.AopLogMenu;

import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Select;


import java.util.List;




public interface UserMapper  {

    @LogAnnotation(operation = "查询全部用户", aopLogMenu = AopLogMenu.LIST)
    @Select("select * from users ")
    List<User> userlist();


    @LogAnnotation(operation = "新增用户", aopLogMenu = AopLogMenu.INSERT)
    @Insert("insert into users(name, age, gender) VALUE(#{name},#{age},#{gender})")
    void adduser(User user);


    @LogAnnotation(operation = "删除用户",aopLogMenu = AopLogMenu.DELETE)

    void deleteByIds(List<Long> ids);


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



     @LogAnnotation(operation = "分页查询用户信息",aopLogMenu = AopLogMenu.LIST)
     List<User> pagefind(PageUserDTO pageUserDTO);



     @LogAnnotation(operation = "查询用户总数",aopLogMenu = AopLogMenu.OTHER)
    Long usertotal(PageUserDTO pageUserDTO);

     @LogAnnotation(operation = "根据用户id查询用户",aopLogMenu = AopLogMenu.LIST)
     @Select("select *  from users where id=#{id}")
    User selectById(Long id);


     @LogAnnotation(operation = "插入一条用户记录",aopLogMenu = AopLogMenu.INSERT)
     @TimeFiledAnnotation(AopLogMenu.INSERT)
     @Insert("insert into users(name, age, gender,  address, city, account, password, create_time, update_time,avatar_url,email,status) VALUES (#{name},#{age},#{gender},#{address},#{city},#{account},#{password},#{createTime},#{updateTime},#{avatarUrl},#{email},#{status})")
    void insertUser(User user);


     @LogAnnotation(operation = "根据邮箱查询用户是否存在",aopLogMenu = AopLogMenu.LIST)
     @Select("select * from users where email=#{email}")
     User getUserByemail(String email);



}



