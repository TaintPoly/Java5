package com.j5shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class J5ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(J5ShopApplication.class, args);
	}

}
