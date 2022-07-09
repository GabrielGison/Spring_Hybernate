package co.develhope.hybernate.services;
import co.develhope.hybernate.entities.Phrase;
import co.develhope.hybernate.repositories.Phrases;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PhraseService {

    @Autowired
    private Phrases phrases;

    public List<Phrase> phraseList() {
        return phrases.findAll();
    }


    public void savePhrase(Phrase car) { phrases.save(car); }

    public Phrase getPhrase(Integer id) {return phrases.findById(id).get();}

    public void deletePhrase(Integer id) {
        phrases.deleteById(id);
    }

    public Integer listNewID(){
        Phrase last = phraseList().get(phraseList().lastIndexOf(phraseList()));
        return last.getId();
    }

    public Phrase randomListID(){
        List<Phrase> givenList = phraseList();
        Random rand = new Random();
        Phrase randomElement = givenList.get(rand.nextInt(givenList.size()));
        return randomElement;
    }

}
