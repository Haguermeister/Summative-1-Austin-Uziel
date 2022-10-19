package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TShirtRepo extends JpaRepository<TShirt, Integer> {
    List<TShirt> findByColor(String color);

    List<TShirt> findBySize(String size);

}
