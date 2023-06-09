package backend.softdle.model.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
