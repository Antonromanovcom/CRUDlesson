package com.antonromanov.springboot.crud.librarycrud.domain.reader;

import com.antonromanov.springboot.crud.librarycrud.domain.order.BookOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@NoArgsConstructor
public class Reader {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reader_seq_gen")
	@SequenceGenerator(name = "reader_seq_gen", sequenceName = "reader_id_seq")
	private long id;

	@Column
	private String fName;

	@Column
	private String phone;

	@Column
	private String address;

	// Один читатель может иметь несколько заказов.
	@Column
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "reader")
	private List<BookOrder> orders;

	public Reader(ReaderController.ReaderDTO dto) {
		this.fName = dto.getFName();
		this.phone = dto.getPhone();
		this.address = dto.getAddress();
	}

	/**
	 * 	Репозиторий.
	 */
	public interface Repo extends JpaRepository<Reader, Long> {
		//List<Reader> findAllByIdIn(Set<Long> ids);
	}
}
