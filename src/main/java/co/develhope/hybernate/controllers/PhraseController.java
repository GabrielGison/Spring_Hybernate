package co.develhope.hybernate.controllers;

import co.develhope.hybernate.entities.Phrase;
import co.develhope.hybernate.repositories.Phrases;
import co.develhope.hybernate.services.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PhraseController {

    @Autowired
    private Phrases phrases;

    @Autowired
    private PhraseService phraseService;

    //INSERT NEW PHRASE
    @PostMapping("/pfu")
    private ResponseEntity<Phrase> addPhrase(
            @RequestParam(name = "author", required = false, defaultValue = "Unknown") String author,
            @RequestParam(name = "line1") String line1,
            @RequestParam(name = "line2", required = false, defaultValue = "") String line2,
            @RequestParam(name = "line3", required = false, defaultValue = "") String line3,
            @RequestParam(name = "line4", required = false, defaultValue = "") String line4,
            @RequestParam(name = "line5", required = false, defaultValue = "") String line5){
        try {
            Phrase phrase = new Phrase();
            phrase.setAuthor(author);
            phrase.setPhrase(line1);
            if (line2 != "") phrase.setPhrase(phrase.getPhrase() +  "" + line2);
            if (line3 != "") phrase.setPhrase(phrase.getPhrase() +  "" + line3);
            if (line4 != "") phrase.setPhrase(phrase.getPhrase() +  "" + line4);
            if (line5 != "") phrase.setPhrase(phrase.getPhrase() +  "" + line5);
            phraseService.savePhrase(phrase);
            return new ResponseEntity<Phrase>(phrase, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Phrase>(HttpStatus.NOT_FOUND);}
    }

    //GET ALL PHRASES
    @GetMapping("/pfu/all")
    private ResponseEntity<List<Phrase>> allPhrases(){
      try{
          return new ResponseEntity<List<Phrase>>(phraseService.phraseList(), HttpStatus.OK);
      } catch (Exception e){
          return new ResponseEntity<List<Phrase>>(HttpStatus.BAD_REQUEST);
      }
    }

    //GET RANDOM PHRASE
    @GetMapping("/pfu")
    private ResponseEntity<Phrase> getRandomPhrase(){
        try{
            return new ResponseEntity<Phrase>(phraseService.randomListID(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Phrase>(HttpStatus.BAD_REQUEST);
        }
    }

    //GET PHRASE FROM ID
    @GetMapping("/pfu/{id}")
    private ResponseEntity<Phrase> getPhrase(@PathVariable Integer id){
        try{
            return new ResponseEntity<Phrase>(phraseService.getPhrase(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Phrase>(HttpStatus.BAD_REQUEST);
        }
    }

    //CHANGE PHRASE
    @PutMapping("/pfu/{id}")
    private ResponseEntity<Phrase> updatePhrase(
            @PathVariable Integer id,
            @RequestParam(name = "new_id", required = false, defaultValue = "00") Integer newId,
            @RequestParam(name = "author", required = false) String author,
            @RequestParam(name = "line1", required = false, defaultValue = "") String line1,
            @RequestParam(name = "line2", required = false, defaultValue = "") String line2,
            @RequestParam(name = "line3", required = false, defaultValue = "") String line3,
            @RequestParam(name = "line4", required = false, defaultValue = "") String line4,
            @RequestParam(name = "line5", required = false, defaultValue = "") String line5){
        try {
            Phrase existPhrase = phraseService.getPhrase(id);
            if (newId != 00) existPhrase.setId(newId);
            if (author == null) existPhrase.setAuthor(existPhrase.getAuthor());
            else existPhrase.setAuthor(author);
            if (line1 != "") existPhrase.setPhrase(line1);
            if (line2 != "") existPhrase.setPhrase(existPhrase.getPhrase() +  "" + line2);
            if (line3 != "") existPhrase.setPhrase(existPhrase.getPhrase() +  "" + line3);
            if (line4 != "") existPhrase.setPhrase(existPhrase.getPhrase() +  "" + line4);
            if (line5 != "") existPhrase.setPhrase(existPhrase.getPhrase() +  "" + line5);
            phraseService.savePhrase(existPhrase);
            return new ResponseEntity<Phrase>(existPhrase, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Phrase>(HttpStatus.BAD_REQUEST);
        }

    }

    //DELETE PHRASE
    @DeleteMapping("/pfy/{id}")
    private ResponseEntity<String> deletePhrase(@PathVariable Integer id){
        try {
            phraseService.deletePhrase(id);
            return new ResponseEntity<String>("Phrase deleted!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("Error",HttpStatus.BAD_REQUEST);
        }
    }
}
