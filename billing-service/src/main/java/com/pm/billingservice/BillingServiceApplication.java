package com.pm.billingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BillingServiceApplication {

    public static void main(String[] args) {
        System.out.println("Hi");
        SpringApplication.run(BillingServiceApplication.class, args);
        System.out.println("Hello");
    }

}
