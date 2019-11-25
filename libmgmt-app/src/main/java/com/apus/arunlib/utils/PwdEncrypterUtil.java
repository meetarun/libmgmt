package com.apus.arunlib.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/**
 * @author Arun Kumar Raju
 */
public class PwdEncrypterUtil {
    private static final long serialVersionUID = 129973243L;
    // Encrypt Password with BCryptPasswordEncoder
    public static String encrytePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "testadmin$1";
        String encrytedPassword = encrytePassword(password);
        System.out.println("Encryted Password: " + encrytedPassword);
    }

    public static Boolean verifyPassword(String rawPassword,String ecnodedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword,ecnodedPassword);
    }
}
