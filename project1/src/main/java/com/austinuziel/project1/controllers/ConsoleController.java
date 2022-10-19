package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Console;
import com.austinuziel.project1.repositories.ConsoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gameConsole")
public class ConsoleController {
    @Autowired
    ConsoleRepo repo;
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getGameConsoles() {return repo.findAll();}

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getGameConsoleById(@PathVariable Integer id) {
        Optional<Console> optional = repo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getGameByManufacturer(@PathVariable String manufacturer) {
        return repo.findByManufacturer(manufacturer);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Console addGameConsole(@RequestBody @Valid Console console) {
        return repo.save(console);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGameConsole(@RequestBody @Valid Console console) {
        repo.save(console);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameConsole(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
