package webBookShelf.application.persistence.entities.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import webBookShelf.application.persistence.tables.UsersRolesTableDesc;
import webBookShelf.application.persistence.tables.UsersTableDesc;

import javax.persistence.*;
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = UsersRolesTableDesc.SYSTEM_NAME,
			joinColumns = @JoinColumn(name = UsersRolesTableDesc.USER_ID_FIELD),
			inverseJoinColumns = @JoinColumn(name = UsersRolesTableDesc.ROLE_ID_FIELD))
	private List<Role> roles;

	@Builder
	public User(String name, String password, String email, List<Role> roles) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}
}
