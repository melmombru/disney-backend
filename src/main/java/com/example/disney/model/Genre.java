package com.example.disney.model;

import com.example.disney.exception.GenreException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code;
    private String name;
    private Long movies;

    public Genre(String name, Long movies) {
        this.name = name;
        this.movies = movies;
    }

    public String getName() {
        return this.name;
    }

    public Long getMovies() {
        return this.movies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovies(Long movies) {
        this.movies = movies;
    }

    public Long getCode() {
        return this.code;
    }
}
