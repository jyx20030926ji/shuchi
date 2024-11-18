package com.testvue.testvue.mapper;



import com.testvue.testvue.annotation.LogAnnotation;
import com.testvue.testvue.annotation.TimeFiledAnnotation;
import com.testvue.testvue.enity.dto.PageBookDTO;
import com.testvue.testvue.enity.po.Book;
import com.testvue.testvue.enity.po.Categories;
import com.testvue.testvue.enity.vo.BookDetailVO;
import com.testvue.testvue.menu.AopLogMenu;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;



public interface BookMapper {


    @TimeFiledAnnotation(AopLogMenu.INSERT)
    @LogAnnotation(operation = "发布图书信息",aopLogMenu = AopLogMenu.INSERT)
    @Insert("insert into book( book_name, book_author, book_status, bookISBN, book_price, user_id, create_time, update_time, description,image_url) VALUES( #{bookName}, #{bookAuthor}, #{bookStatus}, #{bookISBN}, #{bookPrice}, #{userId}, #{createTime},#{updateTime},#{description},#{imageUrl}) ")
    void publishBook(Book book);

    @LogAnnotation(operation = "分页查询图书信息",aopLogMenu = AopLogMenu.LIST)
   List<Book> pagefind(PageBookDTO pageBookDTO);

   @LogAnnotation(operation = "查询符合图书分页条件总数",aopLogMenu = AopLogMenu.LIST)
    Long total(PageBookDTO pageBookDTO);


    @LogAnnotation(operation = "查询图书详细信息",aopLogMenu = AopLogMenu.OTHER)
    @Select("select u.account,u.address,u.age,u.gender,u.city,b.description,b.image_url,b.book_name,b.book_author,b.create_time,b.book_status from book b,users u where b.user_id=u.id and b.id=#{id}")
    BookDetailVO findDetailById(Long id);


    @LogAnnotation(operation = "批量删除图书信息",aopLogMenu = AopLogMenu.DELETE)
    void deleteBookByIds(List<Long> ids);

    @LogAnnotation(operation = "根据图书id查询图书信息")
    @Select("select * from book where id=#{id}")
    Book selectById(Long id);


    @LogAnnotation(operation = "根据id修改图书信息")
    @TimeFiledAnnotation(AopLogMenu.UPDATE)
    void updateById(Book book);


    @Update("update book set book_status=#{status} where id=#{id}")
    void updateStatusById(Long id, Integer status);

    @Select("select * from book_categories")
    List<Categories> getAllCategories();
}
