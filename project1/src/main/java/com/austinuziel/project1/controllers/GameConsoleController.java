package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.GameConsole;
import com.austinuziel.project1.repositories.GameConsoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/GameConsole")
public class GameConsoleController {
    @Autowired
    GameConsoleRepo repo;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<GameConsole> getGameConsoles() {return new ArrayList<>();}
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public GameConsole addGameConsole(@RequestBody GameConsole gameConsole) {return new GameConsole();}

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGameConsole(@RequestBody GameConsole gameConsole) {}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameConsole(@PathVariable Integer id) {}
}
