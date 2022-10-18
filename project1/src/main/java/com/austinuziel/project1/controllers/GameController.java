package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Game;
import com.austinuziel.project1.repositories.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Game")
public class GameController {
    @Autowired
    GameRepo repo;
    
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGames() {return new ArrayList<>();}
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {return new Game();}

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id) {}
}
