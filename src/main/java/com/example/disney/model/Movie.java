package com.example.disney.model;

import com.example.disney.exception.MovieException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code;
    private String title;
    private LocalDate date;
    private int qualification;
    private Long characters;

    public Movie(String title, String date, int qualification, Long characters) {
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

    public Long getCharacters() {
        return this.characters;
    }

    public void setCharacters(Long characters) {
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

    public Long getCode() {
        return this.code;
    }
}
