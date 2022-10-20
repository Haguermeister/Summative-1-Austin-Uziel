package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {


    @Autowired
    private InvoiceRepo invoiceRepo;

    private Invoice invoice;
    private Invoice invoice2;

    @Before
    public void setUp() throws Exception {
        //        ARRANGE
        invoice = new Invoice();
        invoice.setName("Uziel");
        invoice.setStreet("123 ST MAIN");
        invoice.setCity("Dallas");
        invoice.setState("TX");
        invoice.setZipCode(12345);
        invoice.setItemType("Game Console");
        invoice.setTax(5.99);
        invoice.setQuantity(4);
        invoice.setProcessingFee(6.9);
        invoice.setUnit_price(5.99);
        invoice.setSubtotal(23.96);
        invoice.setTotal(361.00);
        invoice.setItemId(1);
        invoiceRepo.deleteAll();

        invoice2 = new Invoice();
        invoice2.setName("Uzi");
        invoice2.setStreet("123 ST Not Main");
        invoice2.setCity("Puebla");
        invoice2.setState("PB");
        invoice2.setZipCode(12345);
        invoice2.setItemType("Game");
        invoice2.setTax(5.99);
        invoice2.setQuantity(4);
        invoice2.setProcessingFee(6.9);
        invoice2.setUnit_price(5.99);
        invoice2.setSubtotal(23.96);
        invoice2.setTotal(361.00);
        invoice2.setItemId(1);
    }

    //    POST ENDPOINTS
    @Test
    public void addGetDeleteInvoice() {
//        ACT
        invoice = invoiceRepo.save(invoice);

        Optional<Invoice> invoice1 = invoiceRepo.findById(invoice.getId());


//        ASSERT
        assertEquals(invoice1.get(), invoice);

        invoiceRepo.deleteById(invoice.getId());

        invoice1 = invoiceRepo.findById(invoice.getId());
        assertFalse(invoice1.isPresent());

    }

    //    GET ENDPOINTS
    @Test
    public void getAllInvoices() {

        invoice = invoiceRepo.save(invoice);

        invoice2 = invoiceRepo.save(invoice2);

        List<Invoice> aList = invoiceRepo.findAll();
        assertEquals(aList.size(), 2);

    }

    @Test
    public void getInvoiceById() {
        Invoice outpuInvoice = invoiceRepo.save(invoice);
        Optional<Invoice> getInvoiceById = invoiceRepo.findById(outpuInvoice.getId());
        assertEquals(Optional.of(invoice), getInvoiceById);
    }

    @Test
    public void getInvoiceByName() {
        Invoice outputInvoice = invoiceRepo.save(invoice);
        List<Invoice> allInvoices = new ArrayList<>(Arrays.asList(outputInvoice));
        List<Invoice> namedList = invoiceRepo.findByName("Uziel");
        assertEquals(namedList, allInvoices);
        assertEquals(1, namedList.size());
    }

}
