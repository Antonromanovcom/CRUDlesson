package com.antonromanov.springboot.crud.librarycrud.domain.book;

import com.antonromanov.springboot.crud.librarycrud.domain.author.Author;
import com.antonromanov.springboot.crud.librarycrud.domain.order.BookOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_gen")
	@SequenceGenerator(name = "book_seq_gen", sequenceName = "book_id_seq")
	private long id; // id

	@Column
	private String name; // Название

	@Column
	private String isbn; // номер ISBN

	@Column
	private String pages; // кол-во страниц

	@Column
	@Enumerated(EnumType.STRING)
	private CoverType coverType; // тип обложки

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "books")
	@JsonIgnore
	private List<Author> authors = new ArrayList<>();

	// Дополнительные поля связи
	@ManyToOne
	@JoinColumn(name = "order_id")
	private BookOrder order;

	public Book(BookController.BookDto dto, List<Author> authorList) {
		this.name = dto.getName();
		this.isbn = dto.getIsbn();
		this.pages = dto.getPages();
		this.coverType = CoverType.valueOf(dto.getCoverType());
		this.authors = authorList;
	}

	// Репозиторий
	public interface Repo extends JpaRepository<Book, Long> {
	}
}
