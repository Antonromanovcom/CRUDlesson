package com.antonromanov.springboot.crud.librarycrud.domain.order;

import com.antonromanov.springboot.crud.librarycrud.domain.book.Book;
import com.antonromanov.springboot.crud.librarycrud.domain.reader.Reader;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class BookOrder {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_order_seq_gen")
	@SequenceGenerator(name = "book_order_seq_gen", sequenceName = "book_order_id_seq")
	private long id;

	@Column
	private String date; // дата, когда взяли книжку

	@Column (columnDefinition = "boolean default true")
	private boolean returned = false; // дата, когда взяли книжку

	// Один заказ может включить много книг, поэтому. заказ - "собственник книг".
	@Column
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
	private List<Book> books;

	// Дополнительные поля связи
	@ManyToOne
	@JoinColumn(name = "reader_id")
	private Reader reader;


}
