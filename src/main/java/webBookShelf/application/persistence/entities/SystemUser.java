package webBookShelf.application.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import webBookShelf.application.utils.validation.FieldMatch;
import webBookShelf.application.utils.validation.ValidEmail;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldMatch(first = "password", second = "matchingPassword", message = "пароли не совпадают")
public class SystemUser {
	private static final String requiredMsg = "обязательно для заполнения";

	@Size(min = 3, message = "минимум 3 символа")
	private String userName;

	@ValidEmail(message = "некорректный email адрес")
	@Size(min = 1, message = requiredMsg)
	private String email;

	@Size(min = 5, message = "минимум 5 символов")
	@Size(max = 25, message = "максимум 20 символов")
	@Pattern(regexp = "^[0-9a-zA-Z!@#$%^&*].*", message = "некорректный формат пароля")
	private String password;

	@Size(min = 1, message = requiredMsg)
	private String matchingPassword;
}
