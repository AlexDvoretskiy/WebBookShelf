package webBookShelf.application.persistence.entities.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import webBookShelf.application.persistence.tables.BooksTableDesc;

import javax.persistence.*;

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

	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private List<Author> authors;

	@Cascade(CascadeType.ALL)
	@OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<Category> categories;

	@Builder
	public Book(String title, String description, List<Author> authors, List<Category> categories, String language, String imageLink, String webReaderLink) {
		this.title = title;
		this.description = description;
		this.authors = authors;
		this.categories = categories;
		this.language = language;
		this.imageLink = imageLink;
		this.webReaderLink = webReaderLink;
	}
}
