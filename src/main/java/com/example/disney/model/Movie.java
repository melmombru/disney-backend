package com.example.disney.model;

import com.example.disney.exception.MovieException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long code_movie;
    private String title;
    private LocalDate date;
    private int qualification;
    @ManyToMany(mappedBy = "movies")
    private List<DisneyCharacter> characters;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    public Movie(String title, String date, int qualification, DisneyCharacter characters) {
        this.title = title;
        this.date = LocalDate.parse(date);
        this.qualification = qualification;
        if (characters != null) {
            this.characters.add(characters);
        }
    }

    public Movie(String title, String date, int qualification) {
        this.title = title;
        this.date = LocalDate.parse(date);
        this.qualification = qualification;
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

    public void setCharacters(DisneyCharacter characters) {
        if (characters != null) {
            this.characters.add(characters);
        }
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
        return this.code_movie;
    }
}
