package com.apus.arunlib.utils;
/**
 * @author Arun Kumar Raju
 */
public class LibMgmtConstants {
    private static final long serialVersionUID = 86489L;
    //1 hour expiration time
    public static final long LIB_TOKEN_EXP_TIME = 3600000;
    public static final String LIB_TOKEN_SIGNING_KEY = "LibMgmt";
    public static final String LIB_TOKEN_BEARER_PREFIX = "Bearer";

    //Roles
    public static final String LIB_ROLE_ADMIN = "ROLE_ADMIN";
    public static final String LIB_ROLE_STUDENT = "ROLE_STUDENT";
    public static final String LIB_ROLE_SUPERVISOR = "ROLE_SUPERVISOR";

    public static final String LIB_BOOK_NOT_FOUND ="Book Not Found";
    public static final String LIB_USER_NOT_FOUND ="User with username {} is not found";
    public static final String LIB_USERID_NOT_FOUND ="User with username %s is not found";

    private LibMgmtConstants() {
    }
}
