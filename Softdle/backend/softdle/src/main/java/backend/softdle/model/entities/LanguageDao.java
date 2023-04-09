package backend.softdle.model.entities;

import backend.softdle.model.services.LanguageSelection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface LanguageDao extends CrudRepository<Language,Long> {
    @Query(value = "SELECT NEW backend.softdle.model.services.LanguageSelection(p.name,p.imagePath) from Language p")

    List<LanguageSelection> getNamesAndImages();
    Optional<Language> findByName(String name);
    Optional<Language> findByIsTheOne(Boolean bool);
}
