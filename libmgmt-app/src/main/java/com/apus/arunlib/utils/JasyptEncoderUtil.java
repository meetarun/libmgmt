package com.apus.arunlib.utils;


import org.jasypt.util.text.BasicTextEncryptor;
/**
 * @author Arun Kumar Raju
 */
public class JasyptEncoderUtil {
    private static final long serialVersionUID = 32346834L;
    public static final String SECRET_KEY = "libmgmtsecret";

    public static String encryptPassowrd(String password) {
        BasicTextEncryptor passwordEncryptor = new BasicTextEncryptor();
        passwordEncryptor.setPassword(SECRET_KEY);
        return passwordEncryptor.encrypt(password);
    }

    public static String decryptPassword(String encryptedText) {
        BasicTextEncryptor passwordDecryptor = new BasicTextEncryptor();
        passwordDecryptor.setPassword(SECRET_KEY);
        return passwordDecryptor.decrypt(encryptedText);
    }

    public static void main(String[] args) {
        String password = "testadmin$1";
        System.out.println("Encrypted Password :"+encryptPassowrd(password));

        String encryptedText = "YEVh8U72aq/QjWw+zM67GQ==";
        System.out.println("Decrypted Password :"+decryptPassword(encryptedText));
    }


}
