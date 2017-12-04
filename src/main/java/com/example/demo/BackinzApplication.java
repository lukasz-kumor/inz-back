package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication

public class BackinzApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackinzApplication.class, args);
	}
}
