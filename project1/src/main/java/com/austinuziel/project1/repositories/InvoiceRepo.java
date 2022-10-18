package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepo extends JpaRepository<Invoice, Integer> {

}
