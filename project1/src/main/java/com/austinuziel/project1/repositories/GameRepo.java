package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepo extends JpaRepository<Game, Integer> {
    List<Game> findByEsrbRating(String esrbRating);

    List<Game> findByStudio(String studio);

    Optional<Game> findByTitle(String title);
}


