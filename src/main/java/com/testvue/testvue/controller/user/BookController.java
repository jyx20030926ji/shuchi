package com.testvue.testvue.controller.user;

import com.testvue.testvue.Service.BookService;
import com.testvue.testvue.enity.dto.PageBookDTO;
import com.testvue.testvue.enity.dto.PublishBookDTO;
import com.testvue.testvue.enity.po.Book;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.po.Result;
import com.testvue.testvue.enity.vo.BookDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("book")
@RequestMapping("/user/books")
public class BookController {

    @Autowired
    private BookService bookService;



    /**
     * 发布图书信息
     * author jyx
     * @param publishBookDTO
     * @return
     */

    @PostMapping
    public Result publishBook(@RequestBody PublishBookDTO publishBookDTO)

    {
        bookService.publishBook(publishBookDTO);
        return Result.success();
    }

    @GetMapping
    public Result<PageResult<Book>> pagefind(@RequestBody PageBookDTO pageBookDTO)
    {

        PageResult<Book> pageResult=bookService.pagefind(pageBookDTO);
        return Result.success(pageResult);

    }

    @GetMapping("/{id}/detail")

    public Result<BookDetailVO> findDetailById(@PathVariable Long id)
    {
        BookDetailVO bookDetailVO=bookService.findDetailById(id);

        return Result.success(bookDetailVO);
    }
    @DeleteMapping("/{ids}")
    public Result  deleteBookByIds(@PathVariable List<Long> ids)
    {

        bookService.deleteBookByIds(ids);

        return Result.success();
    }
    @GetMapping("/{id}")
    public Result<Book> selectById(@PathVariable Long id)

    {
        Book book=bookService.selectById(id);
        return Result.success(book);
    }

    @PutMapping
    public Result updateById(@RequestBody PublishBookDTO publishBookDTO)
    {
        bookService.updateById(publishBookDTO);
        return Result.success();
    }
}
