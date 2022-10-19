package com.austinuziel.project1.repositories;


import com.austinuziel.project1.models.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceRepositoryTest {


    @Autowired
    private InvoiceRepo invoiceRepo;

    @Before
    public void setUp() throws Exception {
        invoiceRepo.deleteAll();
    }

    //    POST ENDPOINTS
    @Test
    public void addGetInvoice() {

//        ARRANGE
        Invoice invoice = new Invoice();
        invoice.setName("Uziel");
        invoice.setStreet("123 ST MAIN");
        invoice.setCity("Dallas");
        invoice.setState("TX");
        invoice.setZipCode(12345);
        invoice.setItemType("Game Console");
        invoice.setTax(5.99F);
        invoice.setQuantity(4);
        invoice.setProcessingFee(6.9F);
        invoice.setUnit_price(5.99F);
        invoice.setSubtotal(23.96F);
        invoice.setTotal(36L);

//        ACT
        invoiceRepo.save(invoice);

        Optional<Invoice> invoice1 = invoiceRepo.findById(invoice.getItemId());


//        ASSERT
        assertEquals(invoice1.get(), invoice);
    }

    //    GET ENDPOINTS
    @Test
    public void getAllInvoices() {

        Invoice invoice = new Invoice();
        invoice.setName("Uziel");
        invoice.setStreet("123 ST MAIN");
        invoice.setCity("Dallas");
        invoice.setState("TX");
        invoice.setZipCode(12345);
        invoice.setItemType("Game Console");
        invoice.setTax(5.99F);
        invoice.setQuantity(4);
        invoice.setProcessingFee(6.9F);
        invoice.setUnit_price(5.99F);
        invoice.setSubtotal(23.96F);
        invoice.setTotal(36L);

        invoice = invoiceRepo.save(invoice);

        invoice = new Invoice();
        invoice.setName("Uzi");
        invoice.setStreet("123 ST Not Main");
        invoice.setCity("Puebla");
        invoice.setState("PB");
        invoice.setZipCode(12345);
        invoice.setItemType("Game");
        invoice.setTax(5.99F);
        invoice.setQuantity(4);
        invoice.setProcessingFee(6.9F);
        invoice.setUnit_price(5.99F);
        invoice.setSubtotal(23.96F);
        invoice.setTotal(36L);

        invoice = invoiceRepo.save(invoice);

        List<Invoice> aList = invoiceRepo.findAll();
        assertEquals(aList.size(), 2);

    }

    @Test
    public void getInvoiceById() {
        Invoice invoice = new Invoice();
        invoice.setName("Uziel");
        invoice.setStreet("123 ST MAIN");
        invoice.setCity("Dallas");
        invoice.setState("TX");
        invoice.setZipCode(12345);
        invoice.setItemType("Game Console");
        invoice.setTax(5.99F);
        invoice.setQuantity(4);
        invoice.setProcessingFee(6.9F);
        invoice.setUnit_price(5.99F);
        invoice.setSubtotal(23.96F);
        invoice.setTotal(36L);

        Invoice outpuInvoice = invoiceRepo.save(invoice);
        Optional<Invoice> getInvoiceById = invoiceRepo.findById(outpuInvoice.getId());
        assertEquals(Optional.of(invoice), getInvoiceById);
    }

    @Test
    public void getInvoiceByUsername() {
        Invoice invoice = new Invoice();
        invoice.setName("Uziel");
        invoice.setStreet("123 ST MAIN");
        invoice.setCity("Dallas");
        invoice.setState("TX");
        invoice.setZipCode(12345);
        invoice.setItemType("Game Console");
        invoice.setTax(5.99F);
        invoice.setQuantity(4);
        invoice.setProcessingFee(6.9F);
        invoice.setUnit_price(5.99F);
        invoice.setSubtotal(23.96F);
        invoice.setTotal(36L);

        Invoice outpuInvoice = invoiceRepo.save(invoice);
        Optional<Invoice> getInvoiceById = invoiceRepo.findById(outpuInvoice.getId());

        List<Optional<Invoice>> allInvoices = new ArrayList<>();
        allInvoices.add(getInvoiceById);

        assertEquals(allInvoices.get(0),getInvoiceById );
        assertEquals( 1,allInvoices.size());
    }
    @Test
    public void getAListOfOptionalInvoicesByUsername() {
        Invoice invoice = new Invoice();
        invoice.setName("Uziel");
        invoice.setStreet("123 ST MAIN");
        invoice.setCity("Dallas");
        invoice.setState("TX");
        invoice.setZipCode(12345);
        invoice.setItemType("Game Console");
        invoice.setTax(5.99F);
        invoice.setQuantity(4);
        invoice.setProcessingFee(6.9F);
        invoice.setUnit_price(5.99F);
        invoice.setSubtotal(23.96F);
        invoice.setTotal(36L);

        Invoice outpuInvoice = invoiceRepo.save(invoice);
        invoice = new Invoice();
        invoice.setName("Uzi");
        invoice.setStreet("123 ST Not Main");
        invoice.setCity("Dallas");
        invoice.setState("TX");
        invoice.setZipCode(12345);
        invoice.setItemType("Game Console");
        invoice.setTax(5.99F);
        invoice.setQuantity(4);
        invoice.setProcessingFee(6.9F);
        invoice.setUnit_price(5.99F);
        invoice.setSubtotal(23.96F);
        invoice.setTotal(36L);

        Invoice outpuInvoice2 = invoiceRepo.save(invoice);
        Optional<Invoice> getInvoiceById = invoiceRepo.findById(outpuInvoice.getId());
        Optional<Invoice> getInvoiceById2 = invoiceRepo.findById(outpuInvoice2.getId());

        List<Optional<Invoice>> allInvoices = new ArrayList<>();
        allInvoices.add(getInvoiceById);
        allInvoices.add(getInvoiceById2);

        assertEquals(allInvoices.get(0),getInvoiceById );
        assertEquals(allInvoices.get(1),getInvoiceById2 );
        assertEquals( 2,allInvoices.size());
    }

}
