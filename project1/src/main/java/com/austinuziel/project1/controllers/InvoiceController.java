package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Invoice;
import com.austinuziel.project1.repositories.InvoiceRepo;
import com.austinuziel.project1.services.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private ServiceLayer serviceLayer;
    @Autowired
    private InvoiceRepo invoiceRepo;

    //    POST REQUESTS
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody Invoice newInvoice) {
        return serviceLayer.buildInvoice(newInvoice);
    }

    //    GET REQUESTS
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Invoice getInvoiceById(@PathVariable Integer id) {
        Optional<Invoice> optional = invoiceRepo.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    //    DELETE REQUESTS
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Integer id) {
        invoiceRepo.deleteById(id);
    }

}
