package com.antonromanov.springboot.crud.librarycrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(considerNestedRepositories = true)
public class LibraryCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryCrudApplication.class, args);
	}
}
