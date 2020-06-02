package webBookShelf.application.persistence.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import webBookShelf.application.persistence.entities.data.Role;


@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String name);
}
