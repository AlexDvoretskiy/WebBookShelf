package webBookShelf.application.persistence.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import webBookShelf.application.persistence.tables.BooksTableDesc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = BooksTableDesc.SYSTEM_NAME)
public class Book {

	@Id
	@Column(name = BooksTableDesc.ID_FIELD)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = BooksTableDesc.TITLE_FIELD)
	private String title;

	@Column(name = BooksTableDesc.DESCRIPTION_FIELD)
	private String description;

	@Column(name = BooksTableDesc.MATURITY_RATING_FIELD)
	private String maturityRating;

	@Column(name = BooksTableDesc.LANGUAGE_FIELD)
	private String language;

	@Column(name = BooksTableDesc.IMAGE_LINK_FIELD)
	private String imageLink;

	@Column(name = BooksTableDesc.WEB_READER_LINK_FIELD)
	private String webReaderLink;

	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<Author> authors;

	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Category> categories;
}
