package webBookShelf.application.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import webBookShelf.application.persistence.entities.data.Book;


public interface BookRepository extends CrudRepository<Book, Long> {
	Book findOneByTitle(String name);
}
