package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Invoice;
import com.austinuziel.project1.services.InvoiceService;
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
    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


    //    POST REQUESTS
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody @Valid Invoice newInvoice) {
        return invoiceService.createNewInvoice(newInvoice);
    }

    //    PUT REQUESTS
    @PutMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editInvoice(@RequestBody @Valid Invoice invoiceToEdit) {
        invoiceService.editInvoice(invoiceToEdit);
    }

//    GET REQUESTS


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }


    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Invoice> getInvoiceById(@PathVariable Integer id) {
        return invoiceService.getInvoiceById(id);
    }
    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> getInvoiceById(@PathVariable String name) {
        return invoiceService.getInvoiceByName(name);
    }


//    DELETE REQUESTS

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable Integer id) {
        invoiceService.deleteInvoiceById(id);
    }

}
