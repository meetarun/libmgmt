/*
 * *
 *  *  @author Arun Kumar Raju
 *  *
 *  *
 */

package com.apus.arunlib.libexception;

/**
 * @author Arun Kumar Raju
 **/
public class LibUserNotFound extends RuntimeException {
    private static final long serialVersionUID = 7656677655656L;

    public LibUserNotFound(String name) {
        super(name);
    }

}
