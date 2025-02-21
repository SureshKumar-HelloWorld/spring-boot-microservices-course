package com.suresh.bookstore.catlog;

import org.springframework.boot.SpringApplication;

public class TestCatlogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(CatlogServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
