package com.apus.arunlib;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Arun Kumar Raju
 */
@EnableEncryptableProperties
@SpringBootApplication
@EnableConfigurationProperties
public class ArunLibProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArunLibProjectApplication.class, args);
    }

}
