package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.TShirt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TShirtRepo extends JpaRepository<TShirt, Integer> {
}
