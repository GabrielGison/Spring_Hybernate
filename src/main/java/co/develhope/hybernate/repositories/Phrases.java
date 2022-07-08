package co.develhope.hybernate.repositories;

import co.develhope.hybernate.entities.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Phrases extends JpaRepository<Phrase, Integer> {

    List<Phrase> findByAuthor(String model);

}
