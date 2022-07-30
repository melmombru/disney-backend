package com.example.disney.service;

import com.example.disney.exception.GenreException;
import com.example.disney.exception.MovieException;
import com.example.disney.model.Genre;
import com.example.disney.model.Movie;
import com.example.disney.repository.GenresRepository;
import com.example.disney.repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenresService {
    @Autowired
    GenresRepository genresRepository;

    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    public Genre loadGenre(Genre genre) {
        validateFields(genre);
        return genresRepository.save(genre);
    }

    public Genre modifyGenre(Genre genre) {
        findByCode(genre.getCode());
        return genresRepository.save(genre);
    }

    public Genre findByCode(Long code) {
        return genresRepository.findByCode(code).orElseThrow(() ->
                new GenreException());
    }

    private void validateFields(Genre genre) {
        if (genre.getName().isEmpty()){
            throw new GenreException();
        }
    }

    public List<Genre> getGenres() {
        return genresRepository.findAll();
    }
}
