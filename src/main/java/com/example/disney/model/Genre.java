package com.example.disney.model;

import com.example.disney.exception.GenreException;

import java.util.List;

public class Genre {
    private String name;
    private List<Movie> movies;

    public Genre(String name, List<Movie> movies) {
        if (name.isEmpty()){
            throw new GenreException();
        }
        this.name = name;
        this.movies = movies;
    }

    public String getName() {
        return this.name;
    }

    public List<Movie> getMovies() {
        return this.movies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
