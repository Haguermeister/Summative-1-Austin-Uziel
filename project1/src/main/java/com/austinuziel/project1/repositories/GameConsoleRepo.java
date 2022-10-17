package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.GameConsole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameConsoleRepo extends JpaRepository<GameConsole, Integer> {
}
