package com.apus.arunlib.controller;

import com.apus.arunlib.entity.BookEntity;
import com.apus.arunlib.libexception.BookNotFoundException;
import com.apus.arunlib.model.Book;
import com.apus.arunlib.model.LibMgmtWrappedResponse;
import com.apus.arunlib.service.BookService;
import com.apus.arunlib.utils.LibMgmtConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author Arun Kumar Raju
 */

@RestController
@RequestMapping("/v1/book")
public class BookController {
    private static final long serialVersionUID = 300L;
    @Autowired
    private BookService bookService;

    private static final Logger logger = LogManager.getLogger(BookController.class);

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN, LibMgmtConstants.LIB_ROLE_STUDENT, LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping("/{id}")
    public  LibMgmtWrappedResponse<Book> getBookById(@PathVariable int id) {
         //TODO : add validation
         LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
         Book book = bookService.getBookById(id);
         if(null != book) {
         response.setStatus(HttpStatus.OK.value());
         response.setMessage("Successfully Fetched the book");
         response.setResult(book);
         return response;
         } else  {
             logger.warn("The book with id {} is not found",id);
             throw new BookNotFoundException(id+"");
         }
    }

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN})
    @PostMapping
    public ResponseEntity saveBook(@RequestBody Book book) {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        try {
            Book savedBook = bookService.saveBook(book);
            response.setStatus(HttpStatus.CREATED.value());
            response.setMessage("Successfully saved the Book");
            response.setResult(savedBook.getBookId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            logger.error("Exception occurred while saving the book {}",e);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMessage("Internal Server Error Saving the book");
            response.setResult(null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN, LibMgmtConstants.LIB_ROLE_STUDENT, LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping("/search")
    public LibMgmtWrappedResponse<Book> searchBookByName(@Param("name") String name) {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        if(!StringUtils.isEmpty(name)) {
            List<Book> bookList = bookService.searchByBookName(name);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Successfully Found Items");
            response.setResult(bookList);
            return response;
        } else {
            response.setMessage("Invalid Search String");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return response;
        }
    }

    //Paginated search of books
   /* @Secured({LibMgmtConstants.LIB_ROLE_ADMIN, LibMgmtConstants.LIB_ROLE_STUDENT, LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping("/searchwithpage")
    public LibMgmtWrappedResponse<Book> searchBookByNamewithPagination(@RequestParam(name = "name") String bookName,
                                                                       @RequestParam(name = "pageno", defaultValue = "0" ) Integer pageNo,
                                                                       @RequestParam(name= "pagesize", defaultValue = "4") Integer pageSize,
                                                                       @RequestParam(name= "sortby", defaultValue ="bookName")  String sortBy) {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        //TODO :Validate pageNo,pageSize field
        if(Boolean.TRUE.equals(LibUtils.validateSortByField(sortBy))) {
            sortBy = LibUtils.getSortByValue(sortBy);
            List<Book> bookList = bookService.searchByBookNameWithPagingandSorting(bookName, pageNo, pageSize, sortBy);
            response.setStatus(HttpStatus.OK.value());
            response.setMessage("Successfully found the search result");
            response.setResult(bookList);
            return response;
        } else {
            logger.warn("Invalid sortBy Field : {}",sortBy);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Invalid sortBy Field : "+sortBy);
            response.setResult(null);
            return response;
        }
    }*/

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN, LibMgmtConstants.LIB_ROLE_STUDENT, LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping("/searchwithpage")
    public Slice<BookEntity> searchBookByNamewithPagination(@RequestParam(name = "name") String bookName, Pageable pageable) {

            return bookService.searchByBookNameWithPagingandSorting(bookName, pageable);

    }

    @Secured({LibMgmtConstants.LIB_ROLE_ADMIN, LibMgmtConstants.LIB_ROLE_SUPERVISOR})
    @GetMapping
    public LibMgmtWrappedResponse<BookEntity> getAllBooks() {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        List<BookEntity> allBooks= bookService.getAllBooks();
        response.setMessage("Successfully retrieved all books");
        response.setStatus(HttpStatus.OK.value());
        response.setResult(allBooks);
        return response;

    }
}
