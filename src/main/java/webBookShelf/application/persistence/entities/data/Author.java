package webBookShelf.application.persistence.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import webBookShelf.application.persistence.tables.AuthorsTableDesc;
import webBookShelf.application.persistence.tables.BooksTableDesc;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Data
@Entity
@NoArgsConstructor
@Table(name = AuthorsTableDesc.SYSTEM_NAME)
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = AuthorsTableDesc.ID_FIELD)
	private Long id;

	@Column(name = AuthorsTableDesc.NAME_FIELD)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = BooksTableDesc.ID_FIELD, insertable = false, updatable = false)
	private Book book;
}
