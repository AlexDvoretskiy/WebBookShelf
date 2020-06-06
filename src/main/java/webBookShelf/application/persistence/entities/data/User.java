package webBookShelf.application.persistence.entities.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import webBookShelf.application.persistence.tables.UsersRolesTableDesc;
import webBookShelf.application.persistence.tables.UsersTableDesc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name = UsersTableDesc.SYSTEM_NAME)
public class User {

	@Id
	@Column(name = UsersTableDesc.ID_FIELD)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name =  UsersTableDesc.NAME_FIELD)
	private String name;

	@Column(name = UsersTableDesc.PASSWORD_FIELD)
	private String password;

	@Column(name = UsersTableDesc.EMAIL_FIELD)
	private String email;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = UsersRolesTableDesc.SYSTEM_NAME,
			joinColumns = @JoinColumn(name = UsersRolesTableDesc.USER_ID_FIELD),
			inverseJoinColumns = @JoinColumn(name = UsersRolesTableDesc.ROLE_ID_FIELD))
	private List<Role> roles;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<UserBook> userBooks;

	@Builder
	public User(String name, String password, String email, List<Role> roles) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}
}
