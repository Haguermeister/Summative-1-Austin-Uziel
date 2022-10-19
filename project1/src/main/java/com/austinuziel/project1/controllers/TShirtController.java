package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Game;
import com.austinuziel.project1.models.TShirt;
import com.austinuziel.project1.repositories.TShirtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tshirt")
public class TShirtController {
    @Autowired
    TShirtRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirts() {
        return new ArrayList<>();
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable String color) {
        return repo.findByColor(color);
    }


    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable String size) {
        return repo.findBySize(size);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt addTShirt(@RequestBody TShirt tShirt) {
        return new TShirt();
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody TShirt tShirt) {
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable Integer id) {
    }
}
