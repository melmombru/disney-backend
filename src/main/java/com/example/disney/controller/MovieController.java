package com.example.disney.controller;

import com.example.disney.model.DisneyCharacter;
import com.example.disney.model.Genre;
import com.example.disney.model.Movie;
import com.example.disney.service.CharacterService;
import com.example.disney.service.MoviesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieController {
    private MoviesService moviesService;

    public MovieController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @PostMapping("/load")
    public ResponseEntity<Movie> loadMovie(@RequestBody Movie body) {
        return new ResponseEntity<>(moviesService.loadMovie(body), HttpStatus.CREATED);
    }

    @PutMapping("/modify")
    public ResponseEntity<Movie> modifyMovie(@RequestBody Movie body) {
        return new ResponseEntity<>(moviesService.modifyMovie(body), HttpStatus.OK);
    }

    @PutMapping("/modify/{idMovie}/{idCharacter}")
    public ResponseEntity<Movie> modifyMovieCharacter(@RequestBody Movie body) {
        return new ResponseEntity<>(moviesService.modifyMovie(body), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Integer code) {
        moviesService.deleteMovie(Long.valueOf(code));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(moviesService.getMovies(), HttpStatus.OK);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<Movie>> findByName(@RequestParam String name) {
        return new ResponseEntity<>(moviesService.findMovieByName(name), HttpStatus.OK);
    }

    @GetMapping("/find/{age}")
    public ResponseEntity<List<Movie>> findByAge(@RequestParam Integer code_genre) {
        return new ResponseEntity<>(moviesService.findMovieByGenre(Long.valueOf(code_genre)), HttpStatus.OK);
    }

    @GetMapping("/find/{idMovie}")
    public ResponseEntity<List<Movie>> findByMovie(@RequestParam String order) {
        if (order.contains("ASC")) {
            return new ResponseEntity<>(moviesService.findMovieByASCOrder(), HttpStatus.OK);
        }
        return new ResponseEntity<>(moviesService.findMovieByDESCOrder(), HttpStatus.OK);
    }
}
