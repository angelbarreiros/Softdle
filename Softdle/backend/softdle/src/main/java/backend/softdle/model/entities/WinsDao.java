package backend.softdle.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinsDao extends PagingAndSortingRepository<Wins,Long>, CrudRepository<Wins,Long> {
    Slice<Wins> findAllByUserUsernameOrderByDateAsc(String username, Pageable pageable);
}
