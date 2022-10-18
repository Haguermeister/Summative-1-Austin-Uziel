package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Game;
import com.austinuziel.project1.repositories.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    GameRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGames() {
        return repo.findAll();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameById(@PathVariable Integer id) {
        Optional<Game> optional= repo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameByTitle(@PathVariable String title) {
        Optional<Game> optional = repo.findByTitle(title);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;    }
    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByStudio(@PathVariable String studio) {
        return repo.findByStudio(studio);
    }
    @GetMapping("/esrb/{esrb}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByESRB(@PathVariable String esrb) {
        return repo.findByEsrbRating(esrb);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGame(@RequestBody Game game) {return repo.save(game);}

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@RequestBody Game game) {
         repo.save(game);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
