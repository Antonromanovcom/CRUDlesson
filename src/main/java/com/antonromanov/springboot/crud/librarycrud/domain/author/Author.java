package com.antonromanov.springboot.crud.librarycrud.domain.author;

import com.antonromanov.springboot.crud.librarycrud.domain.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq_gen")
	@SequenceGenerator(name = "author_seq_gen", sequenceName = "author_id_seq")
	private long id; // id

	@Column
	private String russianName; // Русское наименование

	@Column
	private String englishName; // английское наименование

	// Дополнительное поле связи
	@ManyToMany(cascade =
			CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "authorbook", joinColumns = @JoinColumn(name="author_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
	private List<Book> books = new ArrayList<>();

	public Author(AuthorController.AuthorDto dto) {
		this.russianName = dto.getRName();
		this.englishName = dto.getEName();
	}

	/**
	 * 	Репозиторий.
	 */
	public interface Repo extends JpaRepository<Author, Long> {
		List<Author> findAllByIdIn(Set<Long> ids);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Author author = (Author) o;
		return id == author.id &&
				russianName.equals(author.russianName) &&
				Objects.equals(englishName, author.englishName) &&
				Objects.equals(books, author.books);
	}

	// https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
	@Override
	public int hashCode() {
		return Objects.hash(id, russianName, englishName, books);
	}
}
