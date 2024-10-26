package com.testvue.testvue.controller;

import com.testvue.testvue.Service.BookService;
import com.testvue.testvue.enity.dto.PublishBookDTO;
import com.testvue.testvue.enity.po.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;



public class BookController {

    @Autowired
    private BookService  bookService;



    /**
     * 发布图书信息
     * author jyx
     * @param publishBookDTO
     * @return
     */

    @PostMapping("/admin/books")
    public Result publishBook(@RequestBody PublishBookDTO publishBookDTO)

    {
               bookService.publishBook(publishBookDTO);
               return Result.success();
    }

}
