package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

    List<Invoice> findByName(String name);


}
