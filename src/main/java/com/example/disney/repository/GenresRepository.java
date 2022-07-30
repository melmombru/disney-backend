package com.example.disney.repository;

import com.example.disney.model.DisneyCharacter;
import com.example.disney.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenresRepository extends JpaRepository<Genre, Long> {

//    @Query("FROM Genre g WHERE g.code = :code")
//    Optional<Genre> findByCode(Long code);
}
