package com.example.disney.service;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CharacterMoviePK implements Serializable {
    @Column(name = "code_character")
    private Long code_character;
    @Column(name = "code_movie")
    private Long code_movie;

    public CharacterMoviePK(Long code_character, Long code_movie) {
        this.code_character = code_character;
        this.code_movie = code_movie;
    }

    public CharacterMoviePK() {

    }

    public Long getCharacter(){
        return this.code_character;
    }

    public Long getMovie(){
        return this.code_movie;
    }

    public void setCharacter(Long code) {
        this.code_character = code;
    }

    public void setMovie(Long code) {
        this.code_movie = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterMoviePK that = (CharacterMoviePK) o;
        return getCharacter().equals(that.getCharacter()) && getMovie().equals(that.getMovie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacter(), getMovie());
    }
}
