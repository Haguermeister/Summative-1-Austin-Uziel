package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepo extends JpaRepository<Game, Integer> {
}


