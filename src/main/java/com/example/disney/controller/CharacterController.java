package com.example.disney.controller;

import com.example.disney.model.DisneyCharacter;
import com.example.disney.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/disney")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CharacterController {
    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
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
    public ResponseEntity<DisneyCharacter> modifyCharacter(@PathVariable Integer code) {
        characterService.deleteCharacter(Long.valueOf(code));
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/find") {
//
//    }
}
