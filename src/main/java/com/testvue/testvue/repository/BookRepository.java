package com.testvue.testvue.repository;

import com.testvue.testvue.enity.po.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;

public interface BookRepository {


    List<Book> findByBookNameContainingOrBookAuthorContaining(String bookName, String bookAuthor);


}
