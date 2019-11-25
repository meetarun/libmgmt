package com.apus.arunlib.config;

import com.apus.arunlib.libexception.BookNotFoundException;
import com.apus.arunlib.libexception.LibResourceNotFoundException;
import com.apus.arunlib.libexception.LibUserNotFound;
import com.apus.arunlib.model.LibMgmtWrappedResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * @author Arun Kumar Raju
 */
@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
    private static final long serialVersionUID = 100L;
    private static final Logger logger = LogManager.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<LibMgmtWrappedResponse> bookNotFoundException(Exception ex) {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setResult(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);

    }

    @ExceptionHandler(LibUserNotFound.class)
    public ResponseEntity<LibMgmtWrappedResponse> libUserNotFoundException(Exception ex) {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setResult(null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LibResourceNotFoundException.class)
    public ResponseEntity<LibMgmtWrappedResponse> libResourceNotFoundException(Exception ex) {
        LibMgmtWrappedResponse response = new LibMgmtWrappedResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setResult(null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public LibMgmtWrappedResponse handleNotFoundException(RuntimeException ex){
        logger.error("Exception caught is :{} stack :{}",ex.getMessage(),ex);
        return new LibMgmtWrappedResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Oops, There is an Internal Server Error, Try Again",null);
    }
}
