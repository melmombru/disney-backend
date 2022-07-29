package com.example.disney.model;

import javax.persistence.*;

import com.example.disney.exception.CharacterException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data
@NoArgsConstructor
public class DisneyCharacter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long code;
    private String name;
    private int age;
    private int weight;
    private String history;
//    @OneToMany(cascade = {CascadeType.DETACH})
    private Long movies;

    public DisneyCharacter(String name, int age, int weight, String history, Long movies) {
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.movies = movies;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public int getWeight() {
        return this.weight;
    }

    public String getHistory() {
        return this.history;
    }

    public Long getMovies() {
        return this.movies;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
