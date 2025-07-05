package com.pm.patient_service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientServiceApplication {

	public static void main(String[] args) {
		System.out.println("Hi");
		SpringApplication.run(PatientServiceApplication.class, args);
		System.out.println("Hello");
	}
}


// Paste dependency
// http://localhost:4000/v3/api-docs   , copy paste content
// swagger.io - copy paste , take care of version 3.0.0
