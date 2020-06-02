package webBookShelf.application.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import webBookShelf.application.persistence.entities.data.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findOneByName(String name);
}
