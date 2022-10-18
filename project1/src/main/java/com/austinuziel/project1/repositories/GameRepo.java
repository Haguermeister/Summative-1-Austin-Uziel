package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameRepo extends JpaRepository<Game, Integer> {
    List<Game> findByEsrbRating(String esrbRating);
    List<Game> findByStudio(String studio);
    List<Game> findByTitle(String title);

}


