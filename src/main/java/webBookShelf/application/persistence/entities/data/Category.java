package webBookShelf.application.persistence.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import webBookShelf.application.persistence.tables.BooksTableDesc;
import webBookShelf.application.persistence.tables.CategoriesTableDesc;

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
@Table(name = CategoriesTableDesc.SYSTEM_NAME)
public class Category {

	@Id
	@Column(name = CategoriesTableDesc.ID_FIELD)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = CategoriesTableDesc.NAME_FIELD)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = BooksTableDesc.ID_FIELD, insertable = false, updatable = false)
	private Book book;
}
