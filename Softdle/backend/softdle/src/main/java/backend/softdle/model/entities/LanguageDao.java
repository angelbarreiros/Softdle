package backend.softdle.model.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface LanguageDao extends CrudRepository<Language,Long> {
    Optional<Language> findByName(String name);
    Language findByIsTheOne(Boolean bool);
}
