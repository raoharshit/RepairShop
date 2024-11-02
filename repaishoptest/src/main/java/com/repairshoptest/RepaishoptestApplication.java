package com.repairshoptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.repairshoptest.model.Clerk;
import com.repairshoptest.repository.ClerkRepo;

@SpringBootApplication
public class RepaishoptestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepaishoptestApplication.class, args);
;	}

}
