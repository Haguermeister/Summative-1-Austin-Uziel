package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.TShirt;
import com.austinuziel.project1.repositories.TShirtRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tshirt")
public class TShirtController {
    @Autowired
    TShirtRepo repo;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirts() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirt getTShirtByID(@PathVariable Integer id) {
        Optional<TShirt> optional = repo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
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
    public TShirt addTShirt(@RequestBody @Valid TShirt tShirt) {
        return repo.save(tShirt);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@RequestBody @Valid TShirt tShirt) {
        repo.save(tShirt);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable Integer id) {
        repo.deleteById(id);
    }
}
