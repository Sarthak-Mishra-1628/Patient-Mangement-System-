package com.pm.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthServiceApplication {

    public static void main(String[] args) {
        System.out.println("Hi");
        SpringApplication.run(AuthServiceApplication.class, args);
        System.out.println("Hello");
    }

}
