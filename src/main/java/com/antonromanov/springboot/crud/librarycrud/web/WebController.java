package com.antonromanov.springboot.crud.librarycrud.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

	@GetMapping("/")
	public String homePage() {
		return "index";
	}

	@GetMapping("/books")
	public String booksPage() {
		return "books";
	}

	@GetMapping("/users")
	public String usersPage() {
		return "users";
	}

	@GetMapping("/authors")
	public String authorsPage() {
		return "authors";
	}
}
