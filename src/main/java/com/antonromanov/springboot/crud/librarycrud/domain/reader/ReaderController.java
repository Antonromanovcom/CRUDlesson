package com.antonromanov.springboot.crud.librarycrud.domain.reader;

import com.antonromanov.springboot.crud.librarycrud.domain.author.Author;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/readers")
@AllArgsConstructor
public class ReaderController {

	private final Reader.Repo repo;

	@Data
	@AllArgsConstructor
	@Builder
	public static class ReadersListResponse {
		private final List<Reader> readers;
	}

	@Data
	@RequiredArgsConstructor
	public static class ReaderDTO {
		private String fName;
		private String phone;
		private String address;

	}

	/**
	 * Получить всех читателей.
	 *
	 * @return
	 */
	@GetMapping("/all")
	public ReadersListResponse getAllReaders() {
		List<Reader> readers = new ArrayList<>();
		repo.findAll().forEach(readers::add);
		System.out.println("all authors requested...");
		return ReadersListResponse.builder()
				.readers(readers)
				.build();
	}

	/**
	 * Удалить читателя.
	 *
	 * @return
	 */
	@DeleteMapping
	public ReadersListResponse deleteReader(@RequestParam long id) {
		System.out.println("reader with id = " + id + " will be deleted!");
		repo.deleteById(id);
		List<Reader> readers = new ArrayList<>();
		repo.findAll().forEach(readers::add);
		return ReadersListResponse.builder()
				.readers(readers)
				.build();
	}

	/**
	 * Добавить автора.
	 *
	 * @return
	 */
	@PostMapping(value = "/add", consumes = "application/json")
	public ReadersListResponse addAuthor(@RequestBody ReaderDTO dto) {
		repo.save(new Reader(dto));
		List<Reader> readers = new ArrayList<>();
		repo.findAll().forEach(readers::add);
		return ReadersListResponse.builder()
				.readers(readers)
				.build();
	}
}
