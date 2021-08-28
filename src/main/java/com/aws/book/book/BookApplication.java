package com.aws.book.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookApplication {
	/*
	 * funcing maven..!
	 */ public static void main(String[] args) {
		SpringApplication.run(BookApplication.class, args);
	}

}
