package com.testvue.testvue.Service.impl;

import com.testvue.testvue.Service.CartService;

import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.enity.po.Book;
import com.testvue.testvue.enity.po.Cart;
import com.testvue.testvue.enity.po.ItemCart;
import com.testvue.testvue.enity.vo.CartVO;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.BookMapper;
import com.testvue.testvue.mapper.CartMapper;

import com.testvue.testvue.menu.CodeMessageMenu;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    /**
     * 根据用户id查询购物车列表
     * author jyx
     *
     */

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private BookMapper bookMapper;
    @Override
    public CartVO cartListByUserId() {

        Double total=0.00;



        Cart cart=cartMapper.selectCartByUserId(BaseCont.get().longValue());

        if(cart==null)
        {
            throw new AccountNoExistException(CodeMessageMenu.CART_NOT_EXIST);
        }

        List<ItemCart> itemCartList=cartMapper.selectCartListById(cart.getId());

        for(ItemCart itemCart:itemCartList)
        {
            total=total+itemCart.getBookPrice()*itemCart.getBookNumber();
        }

        return new CartVO(cart.getId(),itemCartList,total);

    }

    @Override
    public void insertInToCart(Long id) {

        Book book = bookMapper.selectById(id);
        if(book==null)
        {
            throw new AccountNoExistException(CodeMessageMenu.Book_NOT_EXIST);
        }

        // 判断购物单是否存在，不存在先生成购物单 然后回显；
        Cart cart = cartMapper.selectCartByUserId(BaseCont.get().longValue());

        if (cart == null) {

            Cart cart1=new Cart();
            cart1.setUserId(BaseCont.get().longValue());

            cartMapper.insertInToCart(cart1);


            ItemCart itemCart = ItemCart.builder().cartId(cart1.getId())
                    .bookNumber(1)
                    .bookPrice(book.getBookPrice())
                    .bookName(book.getBookName())
                    .bookAuthor(book.getBookAuthor())
                    .bookId(book.getId())
                    .imageUrl(book.getImageUrl())
                    .build();

            cartMapper.insertInToCartItem(itemCart);

        }
        if (cart != null) {

            ItemCart itemCart = cartMapper.selectCartItemByBookIdAndItemId(id,cart.getId());

            if (itemCart != null) {
                itemCart.setBookNumber(itemCart.getBookNumber() + 1);
                cartMapper.update(itemCart);
            }
            if (itemCart == null) {



                ItemCart itemCart1 = ItemCart.builder().cartId(cart.getId())
                        .bookNumber(1)
                        .bookPrice(book.getBookPrice())
                        .bookName(book.getBookName())
                        .bookAuthor(book.getBookAuthor())
                        .bookId(book.getId())
                        .imageUrl(book.getImageUrl())
                        .build();
                cartMapper.insertInToCartItem(itemCart1);

            }
        }
    }

    /**
     * 根据书的id扣减购物车中书的数量
     * @param id
     */
    @Override
    public void deduct(Long id) {
        Cart cart = cartMapper.selectCartByUserId(BaseCont.get().longValue());

        if(cart==null)
        {
            throw  new AccountNoExistException(CodeMessageMenu.CART_NOT_EXIST);
        }

        ItemCart itemCart = cartMapper.selectCartItemByBookIdAndItemId(id, cart.getId());

        if(itemCart==null)
        {
            throw  new AccountNoExistException(CodeMessageMenu.BOOK_NOT_EXIST_OF_CART);
        }

        else if (itemCart.getBookNumber()==0){
            cartMapper.deleteByItemId(itemCart.getId());

        }
        else {
             if(itemCart.getBookNumber()==1)
             {
                 cartMapper.deleteByItemId(itemCart.getId());
             }
             else {
                 itemCart.setBookNumber(itemCart.getBookNumber() - 1);
                 cartMapper.update(itemCart);
             }
        }

    }

    @Override
    public void deleteItemById(Long id) {
        cartMapper.deleteByItemId(id);
    }

}
