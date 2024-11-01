package com.testvue.testvue.controller.user;

import com.testvue.testvue.Service.CartService;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/carts")
public class CartController {
    @Autowired
    private CartService cartService;



    /**
     * 根据用户id查询购物车
     *
     * @return
     */
    @GetMapping
    public Result<CartVO> cartListByUserId()
    {
        CartVO cartVO = cartService.cartListByUserId();
        return Result.success(cartVO);
    }
    /** author jyx
     * 根据图书id加入购物车
     */

    @PostMapping("/{id}")
    public Result insertInToCart(@PathVariable Long id)
    {
        cartService.insertInToCart(id);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result deduct(@PathVariable Long id)
    {
        cartService.deduct(id);
        return Result.success();
    }



}
