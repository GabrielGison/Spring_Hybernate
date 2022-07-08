package co.develhope.hybernate.controllers;

import co.develhope.hybernate.DTO.PhraseDTO;
import co.develhope.hybernate.entities.Phrase;
import co.develhope.hybernate.repositories.Phrases;
import co.develhope.hybernate.services.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PhraseController {

    @Autowired
    private Phrases phrases;

    @Autowired
    private PhraseService phraseService;

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
            phrase.setLine1(line1);
            phrase.setLine2(line2);
            phrase.setLine3(line3);
            phrase.setLine4(line4);
            phrase.setLine5(line5);
            return new ResponseEntity<Phrase>(phrase, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Phrase>(HttpStatus.NOT_FOUND);}
    }

    @GetMapping("/pfu/all")
    private ResponseEntity<List<PhraseDTO>> allPhrases(){
      try{
          return new ResponseEntity<List<PhraseDTO>>(phraseService.phraseList(), HttpStatus.OK);
      } catch (Exception e){
          return new ResponseEntity<List<PhraseDTO>>(HttpStatus.BAD_REQUEST);
      }
    }

    @GetMapping("/pfu/{id}")
    private ResponseEntity<Phrase> getPhrase(@PathVariable Integer id){
        try{
            return new ResponseEntity<Phrase>(phraseService.getPhrase(id), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Phrase>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/pfu/{id}")
    private ResponseEntity<Phrase> updatePhrase(
            @PathVariable Integer id,
            @RequestParam(name = "new_id", required = false, defaultValue = "00") Integer newId,
            @RequestParam(name = "author", required = false, defaultValue = "**") String author,
            @RequestParam(name = "line1", required = false, defaultValue = "**") String line1,
            @RequestParam(name = "line2", required = false, defaultValue = "**") String line2,
            @RequestParam(name = "line3", required = false, defaultValue = "**") String line3,
            @RequestParam(name = "line4", required = false, defaultValue = "**") String line4,
            @RequestParam(name = "line5", required = false, defaultValue = "**") String line5){
        try {
            Phrase existPhrase = phraseService.getPhrase(id);
            if (newId != 00) existPhrase.setId(newId);
            if (author != "**") existPhrase.setAuthor(author);
            if (line1 != "**") existPhrase.setLine1(line1);
            if (line2 != "**") existPhrase.setLine2(line2);
            if (line3 != "**") existPhrase.setLine3(line3);
            if (line4 != "**") existPhrase.setLine4(line4);
            if (line5 != "**") existPhrase.setLine5(line5);
            return new ResponseEntity<Phrase>(existPhrase, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<Phrase>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/pfy/{id}")
    private ResponseEntity<String> deletePhrase(@PathVariable Integer id){
        try {
            deletePhrase(id);
            return new ResponseEntity<String>("Phrase deleted!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>("Error",HttpStatus.BAD_REQUEST);
        }
    }
}
