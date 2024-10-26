package com.testvue.testvue.mapper;


import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.enity.po.Book;
import com.testvue.testvue.menu.AopLogMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface BookMapper {


    @TimeFiledAnnotation(AopLogMenu.INSERT)
    @LogAnnotation(operation = "发布图书信息",aopLogMenu = AopLogMenu.INSERT)
    @Insert("insert into book( bookName, book_author, book_status, bookISBN, book_price, user_id, create_time, update_time, description) VALUES( #{bookName}, #{bookAuthor}, #{bookStatus}, #{bookISBN}, #{bookPrice}, #{userId}, #{createTime},#{updateTime},#{description}) ")
    void publishBook(Book book);
}
