package com.example.disney.model;

import com.example.disney.exception.MovieException;

import java.time.LocalDate;
import java.util.List;

public class Movie {
    private String title;
    private LocalDate date;
    private int qualification;
    private List<DisneyCharacter> characters;

    public Movie(String title, String date, int qualification, List<DisneyCharacter> characters) {
        if (title.isEmpty() || qualification <= 0 || qualification > 5) {
            throw new MovieException();
        }
        this.title = title;
        this.date = LocalDate.parse(date);
        this.qualification = qualification;
        this.characters = characters;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public int getQualifications() {
        return this.qualification;
    }

    public List<DisneyCharacter> getCharacters() {
        return this.characters;
    }

    public void setCharacters(List<DisneyCharacter> characters) {
        this.characters = characters;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }
}
