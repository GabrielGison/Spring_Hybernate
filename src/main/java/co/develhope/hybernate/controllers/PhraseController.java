package co.develhope.hybernate.controllers;

import co.develhope.hybernate.entities.Phrase;
import co.develhope.hybernate.services.PhraseService;
import co.develhope.hybernate.views.IndexView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.swing.text.Document;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PhraseController {

    @Autowired
    private PhraseService phraseService;

    //INSERT NEW PHRASE
    @PostMapping("/pfu")
    private ResponseEntity<Phrase> addPhrase(
            @RequestParam(name = "author", required = false, defaultValue = "Unknown") String author,
            @RequestParam(name = "line") String line){
        try {
            Phrase phrase = new Phrase();
            phrase.setAuthor(author);
            phrase.setPhrase(line);
            phraseService.savePhrase(phrase);
            return new ResponseEntity<Phrase>(phrase, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Phrase>(HttpStatus.NOT_FOUND);}
    }

    //**************************************_TEST_ZONE_*******************************************

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String viewHomePage() {
        IndexView index = new IndexView(phraseService,phraseService.randomListID().getId());
        return index.getHtml();
    }

        //**************************************_TEST_ZONE_*******************************************


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
            @RequestParam(name = "line", required = false, defaultValue = "") String line){
        try {
            Phrase existPhrase = phraseService.getPhrase(id);
            if (newId != 00) existPhrase.setId(newId);
            if (author == null) existPhrase.setAuthor(existPhrase.getAuthor());
            else existPhrase.setAuthor(author);
            if (line != "") existPhrase.setPhrase(line);
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
