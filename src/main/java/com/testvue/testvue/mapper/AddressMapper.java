package com.testvue.testvue.mapper;

import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.enity.po.Address;

import com.testvue.testvue.menu.AopLogMenu;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface AddressMapper {

    /**
     * 根据用户id查询用户全部地址
     * @param userId
     * @return
     */
    @LogAnnotation(operation = "根据用户id查询用户全部地址",aopLogMenu = AopLogMenu.LIST)
    @Select("select * from address where user_id=#{userId}")
    List<Address> getAllAddress(Long userId);

    /**
     * 插入新的地址
     * @param address
     */
    @LogAnnotation(operation = "加入新的地址",aopLogMenu = AopLogMenu.INSERT)
    @TimeFiledAnnotation(AopLogMenu.INSERT)
    @Insert("insert into address( name, basic_address, detail_address, phone, status, create_time, update_time, user_id) VALUES(#{name},#{basicAddress},#{detailAddress},#{phone},#{status},#{createTime},#{updateTime},#{userId}) ")
    void insert(Address address);

    /**
     * 根据地址id查询地址信息
     * @param id
     * @return
     */
    @LogAnnotation(operation = "根据地址id查询地址",aopLogMenu = AopLogMenu.LIST)
    @Select("select * from address where id=#{id}")
    Address getAddressById(Long id);

    /**
     * 修改地址信息
     * @param address
     */
    @LogAnnotation(operation = "修改地址信息",aopLogMenu = AopLogMenu.UPDATE)
    @TimeFiledAnnotation(AopLogMenu.UPDATE)
    void updateAddress(Address address);

    /**
     * 批量删除地址信息
     * @param ids
     */
    @LogAnnotation(operation = "批量删除地址信息",aopLogMenu = AopLogMenu.DELETE)
    void deleteAddressByIds(List<Long> ids);

    /**
     * 查询用户默认地址信息
     * @param userId
     * @return
     */
    @LogAnnotation(operation = "查询用户默认地址信息",aopLogMenu = AopLogMenu.LIST)
    @Select("select * from address where status=1 and user_id=#{userId}")
    Address selectDefault(Long userId);
}
