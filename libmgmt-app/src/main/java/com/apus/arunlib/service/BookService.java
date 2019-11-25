package com.apus.arunlib.service;

import com.apus.arunlib.entity.BookEntity;
import com.apus.arunlib.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 * @author Arun Kumar Raju
 */
public interface BookService {
    List<BookEntity> getAllBooks();
    Book getBookById(long bookId);
    Book saveBook(Book book) throws Exception;
    List<Book> searchByBookName(String name);
    //List<Book> searchByBookNameWithPagingandSorting(String bookName, Integer pageNo,Integer pageSize, String sortBy);
    Page<BookEntity> searchByBookNameWithPagingandSorting(String bookName, Pageable pageable);
}
