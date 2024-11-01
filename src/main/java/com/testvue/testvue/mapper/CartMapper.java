package com.testvue.testvue.mapper;


import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.enity.po.Cart;
import com.testvue.testvue.enity.po.ItemCart;

import com.testvue.testvue.menu.AopLogMenu;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface CartMapper {

    /**
     * 根据用户id查询购物车id
     *
     * @param userId
     * @return
     */
    @LogAnnotation(operation = "根据用户id查询购物车列表",aopLogMenu = AopLogMenu.LIST)
    @Select("select * from cart where user_Id=#{userId} ")
    Cart selectCartByUserId(Long userId);

    /**
     * 根据购物车id查询全部在购物车中的图书详情
     *
     * @param cartId
     * @return
     */
   @LogAnnotation(operation = "根据购物车id查询购物车中的图书详情",aopLogMenu = AopLogMenu.LIST)
    @Select("select * from item_cart where cart_id=#{cartId}")
    List<ItemCart> selectCartListById(Long cartId);

    /**
     * 插入购物车数据
     * @param cart
     */
    @TimeFiledAnnotation(value = AopLogMenu.INSERT)
    @LogAnnotation(operation = "插入一条新的记录到购物车表中",aopLogMenu = AopLogMenu.INSERT)
    void insertInToCart(Cart cart);

    /**
     * 根据图书id查询购物车中是否有相应图书
     * @param id
     * @return
     */
    @LogAnnotation(operation = "根据图书id查询用户购物车中是否有该图书存在",aopLogMenu = AopLogMenu.LIST)
    @Select("select * from item_cart where book_id=#{id} and cart_id=#{cartId} ")
    ItemCart selectCartItemByBookIdAndItemId(Long id, @Param("cartId") Long cartId);

    /**
     * 插入图书到购物车中
     * @param itemCart
     */
    @LogAnnotation(operation = "插入图书到购物车中",aopLogMenu = AopLogMenu.INSERT)
    @TimeFiledAnnotation(value = AopLogMenu.INSERT)
    @Insert("insert into item_cart(cart_id, book_name, book_author, book_price, book_number, create_time, update_time, book_id) VALUES (#{cartId},#{bookName},#{bookAuthor},#{bookPrice},#{bookNumber},#{createTime},#{updateTime},#{bookId})") 
    void insertInToCartItem(ItemCart itemCart);


    /** author jyx
     * 更新购物车里的图书数量
     * @param itemCart
     */
    @LogAnnotation(operation = "更新购物车内容",aopLogMenu = AopLogMenu.UPDATE)
    @TimeFiledAnnotation(value = AopLogMenu.UPDATE)
    void update(ItemCart itemCart);


    /**
     * 根据购物车图书id删除图书数据
     * @param id
     */
    @LogAnnotation(operation = "根据购物车id删除",aopLogMenu = AopLogMenu.DELETE)
    @Delete("delete from item_cart where id=#{id}")
    void deleteByItemId(Long id);

    /**
     * 删除用户的购物车列表
      * @param userId
     */
    @LogAnnotation(operation = "删除用户的购物车列表",aopLogMenu = AopLogMenu.DELETE)
    @Delete("delete from cart where user_Id=#{userId}")
    void deleteCartByUserId(Long userId);

 /**
  * 删除购物车中的图书详情
  * @param id
  */
    @LogAnnotation(operation = "根据购物车id删除购物车详情",aopLogMenu = AopLogMenu.DELETE)
    @Delete("delete from item_cart where cart_id=#{id}")
    void deleteByCartId(Long id);
}
