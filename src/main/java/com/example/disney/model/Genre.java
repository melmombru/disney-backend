package com.example.disney.model;

import com.example.disney.exception.GenreException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code_genre;
    private String name;
    @OneToMany(mappedBy = "genre")
    private List<Movie> movies;

    public Genre(String name, Movie movie) {
        this.name = name;
        if (movie != null) {
            this.movies.add(movie);
        }
    }

    public Genre(String name) {
        this.name = name;
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

    public void setMovies(Movie movie) {
        this.movies.add(movie);
    }

    public Long getCode() {
        return this.code_genre;
    }
}
