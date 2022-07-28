package com.example.disney.repository;

import com.example.disney.model.DisneyCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<DisneyCharacter, Long> {
    @Query("FROM DisneyCharacter d WHERE d.code = :code")
    Optional<DisneyCharacter> findByCode(Long code);
}
