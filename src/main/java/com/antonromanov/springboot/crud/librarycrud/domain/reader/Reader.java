package com.antonromanov.springboot.crud.librarycrud.domain.reader;

import com.antonromanov.springboot.crud.librarycrud.domain.book.Book;
import com.antonromanov.springboot.crud.librarycrud.domain.order.BookOrder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
public class Reader {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reader_seq_gen")
	@SequenceGenerator(name = "reader_seq_gen", sequenceName = "reader_id_seq")
	private long id;

	@Column
	private String fullName;

	@Column
	private String phone;

	@Column
	private String address;

	// Один читатель может иметь несколько заказов.
	@Column
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "reader")
	private List<BookOrder> orders;
}
