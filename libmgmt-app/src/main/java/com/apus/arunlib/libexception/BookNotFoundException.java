/*
 * *
 *  *  @author Arun Kumar Raju
 *  *
 *  *
 */

package com.apus.arunlib.libexception;

import com.apus.arunlib.utils.LibMgmtConstants;

/**
 * @author Arun Kumar Raju
 **/
public class BookNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 42234657656L;
    public BookNotFoundException(String name) {
        super(LibMgmtConstants.LIB_BOOK_NOT_FOUND +" with id :"+ name);
    }
}
