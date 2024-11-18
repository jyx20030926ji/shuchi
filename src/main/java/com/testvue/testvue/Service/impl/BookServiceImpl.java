package com.testvue.testvue.Service.impl;
import com.testvue.testvue.Service.BookService;
import com.testvue.testvue.basecont.BaseCont;
import com.testvue.testvue.enity.dto.PageBookDTO;
import com.testvue.testvue.enity.dto.PublishBookDTO;
import com.testvue.testvue.enity.po.Book;
import com.testvue.testvue.enity.po.Categories;
import com.testvue.testvue.enity.po.PageResult;
import com.testvue.testvue.enity.vo.BookDetailVO;
import com.testvue.testvue.exception.AccountNoExistException;
import com.testvue.testvue.mapper.BookMapper;
import com.testvue.testvue.menu.CodeMessageMenu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

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

        //TODO 把图片路径传到阿里云，阿里云生成的路径传给本地

        Book book=new Book();

        BeanUtils.copyProperties(publishBookDTO, book);

        book.setUserId(BaseCont.get().longValue());

        bookMapper.publishBook(book);

    }

    /**
     * 分页条件查询图书
     * @param pageBookDTO
     * @return
     */
    @Transactional
    @Override
    public PageResult<Book> pagefind(PageBookDTO pageBookDTO) {
     //   PageHelper.startPage(pageBookDTO.getCurrentPage(),pageBookDTO.getPageSize());

            Long total=bookMapper.total(pageBookDTO);

            pageBookDTO.setCurrentPage((pageBookDTO.getCurrentPage()-1)*pageBookDTO.getPageSize());

            List<Book> bookList=bookMapper.pagefind(pageBookDTO);

           return new PageResult<>(bookList,total);

    }

    /** author jyx
     * 查询图书详细信息
     * @param id
     * @return
     */
    @Override
    public BookDetailVO findDetailById(Long id) {

        Book book = bookMapper.selectById(id);

        if(book == null) {
            throw new AccountNoExistException(CodeMessageMenu.Book_NOT_EXIST);
        }
        BookDetailVO bookDetailVO = bookMapper.findDetailById(id);

        return bookDetailVO;

    }

    /**
     * 批量删除图书信息
     * @param ids
     */
    @Override
    public void deleteBookByIds(List<Long> ids) {


        for(Long id:ids)
        {
            Book book = bookMapper.selectById(id);
             if(book==null)
             {
                 throw new AccountNoExistException(CodeMessageMenu.Book_NOT_EXIST);
             }
        }
        bookMapper.deleteBookByIds(ids);
    }

    @Override
    public Book selectById(Long id) {
       return  bookMapper.selectById(id);
    }

    @Override
    public void updateById(PublishBookDTO publishBookDTO) {
        Book book=new Book();

        BeanUtils.copyProperties(publishBookDTO,book);

        bookMapper.updateById(book);


    }

    @Override
    public void updateStatusById(Long id, Integer status) {
        bookMapper.updateStatusById(id,status);
    }

    @Override
    public List<Categories> getAllCategories() {
      return  bookMapper.getAllCategories();
    }


}
