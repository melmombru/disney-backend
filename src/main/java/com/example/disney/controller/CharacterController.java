package com.example.disney.controller;

import com.example.disney.model.DisneyCharacter;
import com.example.disney.model.Genre;
import com.example.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

//    public CharacterController(CharacterService characterService) {
//        this.characterService = characterService;
//    }

    public CharacterController() {
    }

    @PostMapping("/load")
    public ResponseEntity<DisneyCharacter> loadCharacter(@RequestBody DisneyCharacter body) {
        return new ResponseEntity<>(characterService.loadCharacter(body), HttpStatus.CREATED);
    }

    @PutMapping("/modify")
    public ResponseEntity<DisneyCharacter> modifyCharacter(@RequestBody DisneyCharacter body) {
        return new ResponseEntity<>(characterService.modifyCharacter(body), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<DisneyCharacter> deleteCharacter(@PathVariable Integer code) {
        characterService.deleteCharacter(Long.valueOf(code));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<DisneyCharacter>> getCharacters() {
        return new ResponseEntity<>(characterService.getCharacters(), HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<DisneyCharacter>> findByName(@RequestParam String name) {
        return new ResponseEntity<>(characterService.findCharacterByName(name), HttpStatus.OK);
    }

    @GetMapping("/find/{age}")
    public ResponseEntity<List<DisneyCharacter>> findByAge(@RequestParam Integer age) {
        return new ResponseEntity<>(characterService.findCharacterByAge(age), HttpStatus.OK);
    }

//    @GetMapping("/find/{idMovie}")
//    public ResponseEntity<List<DisneyCharacter>> findByMovie(@RequestParam Integer code_movie) {
//        return new ResponseEntity<>(characterService.findCharacterByMovie(Long.valueOf(code_movie)), HttpStatus.OK);
//    }
}
