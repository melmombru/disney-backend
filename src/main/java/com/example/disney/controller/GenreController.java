package com.example.disney.controller;

import com.example.disney.model.Genre;
import com.example.disney.model.Movie;
import com.example.disney.service.GenresService;
import com.example.disney.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GenreController {
    @Autowired
    private GenresService genresService;

    public GenreController() {

    }

    @PostMapping("/load")
    public ResponseEntity<Genre> loadGenre(@RequestBody Genre body) {
        return new ResponseEntity<>(genresService.loadGenre(body), HttpStatus.CREATED);
    }

    @PutMapping("/modify")
    public ResponseEntity<Genre> modifyGenre(@RequestBody Genre body) {
        return new ResponseEntity<>(genresService.modifyGenre(body), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Genre>> getGenres() {
        return new ResponseEntity<>(genresService.getGenres(), HttpStatus.OK);
    }
}
