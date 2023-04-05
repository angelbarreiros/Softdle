package backend.softdle.model.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface UserDao extends PagingAndSortingRepository<User, Long>, CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
