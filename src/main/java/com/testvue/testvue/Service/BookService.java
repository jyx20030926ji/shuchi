package com.testvue.testvue.Service;

import com.testvue.testvue.enity.dto.PageBookDTO;
import com.testvue.testvue.enity.dto.PublishBookDTO;
import com.testvue.testvue.enity.po.Book;
import com.testvue.testvue.enity.po.Categories;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.vo.BookDetailVO;

import java.util.List;

public interface BookService {
    void publishBook(PublishBookDTO publishBookDTO);


    PageResult<Book> pagefind(PageBookDTO pageBookDTO);

    BookDetailVO findDetailById(Long id);

    void deleteBookByIds(List<Long> ids);

    Book selectById(Long id);

    void updateById(PublishBookDTO publishBookDTO);

    void updateStatusById(Long id, Integer status);

    List<Categories> getAllCategories();
}
