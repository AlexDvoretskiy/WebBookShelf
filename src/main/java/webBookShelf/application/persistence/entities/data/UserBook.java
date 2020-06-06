package webBookShelf.application.persistence.entities.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import webBookShelf.application.persistence.tables.UserBooksTableDesc;

import javax.persistence.*;

import java.util.Date;


@Data
@Entity
@NoArgsConstructor
@Table(name = UserBooksTableDesc.SYSTEM_NAME)
public class UserBook {

	@Id
	@Column(name = UserBooksTableDesc.ID_FIELD)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Cascade(CascadeType.ALL)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = UserBooksTableDesc.USER_ID)
	private User user;

	@OneToOne
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = UserBooksTableDesc.BOOK_ID_FIELD)
	private Book book;

	@Column(name = UserBooksTableDesc.BOOK_GROUP_FIELD)
	private String group;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = UserBooksTableDesc.ADD_DATE_FIELD)
	private Date addDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = UserBooksTableDesc.CLOSE_DATE_FIELD)
	private Date closeDate;

	@Column(name = UserBooksTableDesc.ACTIVE_FIELD)
	private boolean isActive = true;


	public UserBook(User user, Book book, String group) {
		this.user = user;
		this.book = book;
		this.group = group;
	}

	public UserBook(User user, Book book) {
		this.user = user;
		this.book = book;
	}

	public UserBook(User user) {
		this.user = user;
	}
}
