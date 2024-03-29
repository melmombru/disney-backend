package com.example.disney.repository;

import com.example.disney.model.DisneyCharacter;
import com.example.disney.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Long> {
//    @Query("FROM Movie m WHERE m.code_movie = :code_movie")
//    Optional<Movie> findByCode(Long code_movie);

    @Query("FROM Movie m WHERE m.title = :title")
    List<Movie> getMovieName(String title);
}
