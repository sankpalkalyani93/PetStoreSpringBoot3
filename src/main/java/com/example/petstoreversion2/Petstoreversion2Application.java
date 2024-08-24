package com.example.petstoreversion2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.petstoreversion2")
public class Petstoreversion2Application {

	public static void main(String[] args) {
		SpringApplication.run(Petstoreversion2Application.class, args);
	}

}
