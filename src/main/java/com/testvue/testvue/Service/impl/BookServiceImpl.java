package com.testvue.testvue.Service.impl;

import com.testvue.testvue.Service.BookService;
import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.enity.dto.PublishBookDTO;
import com.testvue.testvue.enity.po.Book;
import com.testvue.testvue.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    /**
     * 发布图书
     * @author jyx
     * @param publishBookDTO
     */

    @Override
    public void publishBook(PublishBookDTO publishBookDTO) {

        Book book=new Book();

        BeanUtils.copyProperties(publishBookDTO, book);

        book.setUserId(BaseCont.get().longValue());

        bookMapper.publishBook(book);

    }
}
