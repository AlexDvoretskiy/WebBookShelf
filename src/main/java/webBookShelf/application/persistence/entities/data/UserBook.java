package webBookShelf.application.persistence.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import webBookShelf.application.persistence.tables.BooksTableDesc;
import webBookShelf.application.persistence.tables.UserBooksTableDesc;
import webBookShelf.application.persistence.tables.UsersTableDesc;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.sql.Timestamp;


@Data
@Entity
@NoArgsConstructor
@Table(name = UserBooksTableDesc.SYSTEM_NAME)
public class UserBook {

	@Id
	@Column(name = UserBooksTableDesc.ID_FIELD)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = UsersTableDesc.ID_FIELD, insertable = false, updatable = false)
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = BooksTableDesc.ID_FIELD, insertable = false, updatable = false)
	private Book book;

	@Column(name = UserBooksTableDesc.BOOK_GROUP)
	private String group;

	@Column(name = UserBooksTableDesc.ADD_DATE_FIELD)
	private Timestamp add_date;

	@Column(name = UserBooksTableDesc.CLOSE_DATE_FIELD)
	private Timestamp close_date;

	@Column(name = UserBooksTableDesc.ACTIVE_FIELD)
	private boolean isActive;
}
