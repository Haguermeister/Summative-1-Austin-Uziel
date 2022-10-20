package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.ProcessingFee;
import com.austinuziel.project1.models.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessingFeeRepo extends JpaRepository<ProcessingFee, Integer> {
    Optional<ProcessingFee> findByProductType(String productType);
}