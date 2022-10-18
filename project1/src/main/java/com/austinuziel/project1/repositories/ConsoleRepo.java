package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConsoleRepo extends JpaRepository<Console, Integer> {
    List<Console> findByManufacturer(String manufacturer);

}
