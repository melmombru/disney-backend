package com.example.disney.service;

import com.example.disney.exception.CharacterException;
import com.example.disney.exception.MovieException;
import com.example.disney.model.DisneyCharacter;
import com.example.disney.model.Movie;
import com.example.disney.repository.CharacterRepository;
import com.example.disney.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class MoviesService {
    @Autowired
    MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    public Movie loadMovie(Movie movie) {
        validateFields(movie);
        return moviesRepository.save(movie);
    }

    public Movie modifyMovie(Movie movie) {
        findByCode(movie.getCode());
        return moviesRepository.save(movie);
    }

    @Transactional
    public Movie findByCode(Long code) {
        return moviesRepository.findById(code).orElseThrow(() ->
                new MovieException());
    }

    public void deleteMovie(Long code) {
        Movie movie = findByCode(code);
        moviesRepository.delete(movie);
    }

    private void validateFields(Movie movie) {
        if (movie.getTitle().isEmpty() || movie.getQualifications() <= 0 || movie.getQualifications() > 5) {
            throw new MovieException();
        }
    }

    public List<Movie> getMovies() {
        return moviesRepository.findAll();
    }

    public List<Movie> findMovieByName(String name) {
        List<Movie> movies = moviesRepository.getMovieName(name);
        return movies;
    }

    public List<Movie> findMovieByGenre(Long code) {
        return null;
    }

    public List<Movie> findMovieByASCOrder() {
        List<Movie> movies = moviesRepository.findAll();
        ascSort(movies);
        return movies;
    }

    public List<Movie> findMovieByDESCOrder() {
        List<Movie> movies = moviesRepository.findAll();
        descSort(movies);
        return movies;
    }

    private void ascSort(List<Movie> movies) {
        Collections.sort(movies, Comparator.comparing(Movie::getTitle));
    }

    private void descSort(List<Movie> movies) {
        Collections.sort(movies, Comparator.comparing(Movie::getTitle).reversed());
    }
}
