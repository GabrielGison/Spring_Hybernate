package co.develhope.hybernate.services;
import co.develhope.hybernate.DTO.PhraseDTO;
import co.develhope.hybernate.entities.Phrase;
import co.develhope.hybernate.repositories.Phrases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhraseService {

    @Autowired
    private Phrases phrases;

    public List<PhraseDTO> phraseList() {
        return phrases.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private PhraseDTO convertEntityToDTO(Phrase phrase){
        PhraseDTO phraseDTO = new PhraseDTO();
        phraseDTO.setId(phrase.getId());
        phraseDTO.setAuthor(phrase.getAuthor());
        phraseDTO.setLine1(phrase.getLine1());
        phraseDTO.setLine1(phrase.getLine2());
        phraseDTO.setLine1(phrase.getLine3());
        phraseDTO.setLine1(phrase.getLine4());
        phraseDTO.setLine1(phrase.getLine5());

        return phraseDTO;
    }

    public void savePhrase(Phrase car) { phrases.save(car); }

    public Phrase getPhrase(Integer id) {return phrases.findById(id).get();}

    public void deleteCar(Integer id) {
        phrases.deleteById(id);
    }

}
