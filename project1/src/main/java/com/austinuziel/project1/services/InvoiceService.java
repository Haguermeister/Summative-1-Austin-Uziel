package com.austinuziel.project1.services;

import com.austinuziel.project1.models.Invoice;
import com.austinuziel.project1.repositories.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private ServiceLayer serviceLayer;

    //    CREATED METHODS
    public Invoice createNewInvoice(Invoice invoice) {
        return serviceLayer.
    }

    //    UPDATED METHODS
//    public void editInvoice(Invoice invoiceToBeEdited) {
//        finds within the database an invoice with that id and return it
//       if not found throws a null object
//        invoiceRepo.save(invoiceToBeEdited);
//    }

    //    GET METHODS
    public List<Invoice> getAllInvoices() {
        return invoiceRepo.findAll();
    }


    public Optional<Invoice> getInvoiceById(int id) {
        return invoiceRepo.findById(id);
    }

    public List<Invoice> getInvoiceByName(String name) {
        return invoiceRepo.findByName(name);
    }

//    DELETE METHODS

    public void deleteInvoiceById(int id) {
        invoiceRepo.deleteById(id);
    }

}
