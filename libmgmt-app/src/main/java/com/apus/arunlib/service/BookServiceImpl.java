package com.apus.arunlib.service;

import com.apus.arunlib.entity.BookEntity;
import com.apus.arunlib.libexception.BookNotFoundException;
import com.apus.arunlib.model.Book;
import com.apus.arunlib.repository.BookPagingRepository;
import com.apus.arunlib.repository.BookRepository;
import com.apus.arunlib.utils.LibUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Arun Kumar Raju
 */
@Transactional
@Service(value="bookService")
public class BookServiceImpl implements BookService {
    private static final long serialVersionUID = 523623743L;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookPagingRepository bookPagingRepository;

    private static final Logger logger = LogManager.getLogger(BookServiceImpl.class);

    @Override
    public List<BookEntity> getAllBooks() {
        List<BookEntity> allBooksList = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(allBooksList::add);
        return allBooksList;
    }

    @Override
    public Book getBookById(long bookId) {
        BookEntity bookEntity = bookRepository.getBookByBookId(bookId);
        if(null != bookEntity) {
            logger.info("Fetched the book with id: {}", bookEntity.getBookId());
            return LibUtils.convertBEtoBook(bookEntity);
        } else {
            throw new BookNotFoundException(bookId+"");
        }
    }

    @Override
    public Book saveBook(Book book) throws Exception {
        BookEntity bookEntity = LibUtils.convertBooktoBE(book);
        Book savedBook = LibUtils.convertBEtoBook(bookRepository.save(bookEntity));
        logger.info("Saved the book with id :{}",savedBook.getBookId());
        return savedBook;
    }

    @Override
    public List<Book> searchByBookName(String name) {
        List<Book> bookList = LibUtils.convertBEListToBookList(bookRepository.findBookEntitiesByBookNameContainingIgnoreCase(name));
        logger.info("Total found items are for the keyword :{} are :{}",name,bookList.size());
        return bookList;
    }

/*    @Override
    public List<Book> searchByBookNameWithPagingandSorting(String bookName, Integer pageNo,Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Slice<BookEntity> pagedResult = bookRepository.findBookEntitiesByBookNameContainingIgnoreCase(bookName,paging);
        return LibUtils.convertBEListToBookList(pagedResult.getContent());
    }*/

    @Override
    public Page<BookEntity> searchByBookNameWithPagingandSorting(String bookName, Pageable paging) {
        Page<BookEntity> pagedResult = bookPagingRepository.findBookEntitiesByBookNameContainingIgnoreCase(bookName,paging);
        return pagedResult;
    }
}
