package backend.softdle.model.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface UserDao extends PagingAndSortingRepository<User, Long>, CrudRepository<User,Long> {

}
