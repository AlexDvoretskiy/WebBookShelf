package webBookShelf.application.services.dataServices.interfaces;


import org.springframework.security.core.userdetails.UserDetailsService;

import webBookShelf.application.persistence.entities.SystemUser;
import webBookShelf.application.persistence.entities.data.User;


public interface UserService extends UserDetailsService {
	User findByUserName(String userName);
	void save(SystemUser systemUser);
}
