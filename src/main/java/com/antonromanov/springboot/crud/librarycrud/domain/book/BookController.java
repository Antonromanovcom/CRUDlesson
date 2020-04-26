package com.antonromanov.springboot.crud.librarycrud.domain.book;

import com.antonromanov.springboot.crud.librarycrud.domain.author.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookController {

	private final Book.Repo bookRepo;
	private final Author.Repo authorRepo;

	@Data
	@AllArgsConstructor
	@Builder
	public static class BookListResponse {
		private final List<Book> bookList;
	}

	@Data
	@AllArgsConstructor
	public static class BookDto {
		private final String name;
		private final String isbn;
		private final String pages;
		private final String coverType;
		private final Set<Integer> authorIdList;
	}

	/**
	 * Получить все книги.
	 *
	 * @return
	 */
	@GetMapping("/all")
	public BookListResponse getAllBooks() {
		List<Book> bookList = new ArrayList<>();
		bookRepo.findAll().forEach(bookList::add);
		System.out.println("all books requested...");
		return BookListResponse.builder()
				.bookList(bookList)
				.build();
	}

	/**
	 * Добавить книгу.
	 *
	 * @return
	 */
	@PostMapping("/add")
	@Transactional
	public BookListResponse addAuthor(@RequestBody BookController.BookDto dto) {

		List<Author> authorList = authorRepo.findAllByIdIn(dto.authorIdList
				.stream()
				.mapToLong(Long::valueOf)
				.boxed()
				.collect(Collectors.toSet()));

		System.out.println("Отобрали авторов");

		final Book newBook = bookRepo.saveAndFlush(new Book(dto, authorList));

		System.out.println("Сохранили книгу");

		authorList.forEach(author -> {
			if (author.getBooks().size() == 0) {
				author.setBooks(Arrays.asList(newBook));
			} else {
				author.getBooks().add(newBook);
			}
		});

		System.out.println("Выставили автору книгу");

		List<Book> bookList = new ArrayList<>();
		bookRepo.findAll().forEach(bookList::add);
		return BookListResponse.builder()
				.bookList(bookList)
				.build();
	}
}
