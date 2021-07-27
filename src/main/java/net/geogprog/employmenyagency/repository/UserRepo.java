package net.geogprog.employmenyagency.repository;

import net.geogprog.employmenyagency.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);
}
