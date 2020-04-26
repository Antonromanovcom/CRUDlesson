package com.antonromanov.springboot.crud.librarycrud.domain.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/author")
@AllArgsConstructor
public class AuthorController {

	private final Author.Repo repo;

	@Data
	@AllArgsConstructor
	@Builder
	public static class AuthorListResponse {
		private final List<Author> authorList;
	}

	@Data
	@AllArgsConstructor
	public static class AuthorDto {
		private final String rName; //Russian Name
		private final String eName; //English Name
	}

	/**
	 * Получить всех авторов.
	 *
	 * @return
	 */
	@GetMapping("/all")
	public AuthorListResponse getAllBooks() {
		List<Author> authorList = new ArrayList<>();
		repo.findAll().forEach(authorList::add);
		System.out.println("all authors requested...");
		return AuthorListResponse.builder()
				.authorList(authorList)
				.build();
	}

	/**
	 * Добавить автора.
	 *
	 * @return
	 */
	@PostMapping("/add")
	public AuthorListResponse addAuthor(@RequestBody AuthorDto dto) {
		System.out.println("r-name = " + dto.rName);
		repo.save(new Author(dto));
		List<Author> authorList = new ArrayList<>();
		repo.findAll().forEach(authorList::add);
		return AuthorListResponse.builder()
				.authorList(authorList)
				.build();
	}

	/**
	 * Удалить автора.
	 *
	 * @return
	 */
	@DeleteMapping
	public AuthorListResponse deleteAuthor(@RequestParam long id) {
		System.out.println("author with id = " + id + " will be deleted!");
		repo.deleteById(id);
		List<Author> authorList = new ArrayList<>();
		repo.findAll().forEach(authorList::add);
		return AuthorListResponse.builder()
				.authorList(authorList)
				.build();
	}
}
