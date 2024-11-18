package com.testvue.testvue.Service;


import com.testvue.testvue.enity.vo.CartVO;

public interface CartService {


    CartVO cartListByUserId();

    void insertInToCart(Long id);

    void deduct(Long id);

    void deleteItemById(Long id);
}
