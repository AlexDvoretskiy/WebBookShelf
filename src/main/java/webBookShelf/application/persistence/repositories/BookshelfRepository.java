package webBookShelf.application.persistence.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import webBookShelf.application.persistence.entities.data.UserBook;

import java.util.List;


@Repository
public interface BookshelfRepository extends CrudRepository<UserBook, Long> {

	@Query(value = "select ub.* from user_books ub join users u on u.id = ub.user_id where u.name = ?1", nativeQuery = true)
	List<UserBook> getAllByUserName(String username);
}
