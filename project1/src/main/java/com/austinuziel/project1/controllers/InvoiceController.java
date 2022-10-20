package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Invoice;
import com.austinuziel.project1.repositories.InvoiceRepo;
import com.austinuziel.project1.services.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {


    //    SERVICES
    @Autowired
    private ServiceLayer serviceLayer;
    private InvoiceRepo repo;

    //    POST REQUESTS
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice newInvoice) {
        return serviceLayer.buildInvoice(newInvoice);
    }

    //    GET REQUESTS
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return repo.findAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable Integer id) {
        Optional<Invoice> optional = repo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //    DELETE REQUESTS
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Integer id) {
        repo.deleteById(id);
    }

}
