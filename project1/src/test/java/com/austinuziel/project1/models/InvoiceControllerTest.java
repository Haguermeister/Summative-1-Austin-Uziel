package com.austinuziel.project1.models;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class InvoiceControllerTest {

    Invoice inputInvoice;
    Invoice outputInvoice;

    @Before
    public void setUp() {
        inputInvoice = new Invoice(
                1, "Uziel", "123 ST MAIN", "Dallas", "TX", "12345", "Game Console", 1,
                4, 5.99, 23.96, new BigDecimal("6.9"), 5.99, 364.0
        );

        outputInvoice = new Invoice();
        //        ACT
        outputInvoice.setInvoiceId(1);
        outputInvoice.setName("Uziel");
        outputInvoice.setStreet("123 ST MAIN");
        outputInvoice.setCity("Dallas");
        outputInvoice.setState("TX");
        outputInvoice.setZipcode("12345");
        outputInvoice.setItemType("Game Console");
        outputInvoice.setItemId(1);
        outputInvoice.setQuantity(4);
        outputInvoice.setUnitPrice(5.99);
        outputInvoice.setSubtotal(23.96);
        outputInvoice.setTax(new BigDecimal("6.9"));
        outputInvoice.setProcessingFee(5.99);
        outputInvoice.setTotal(364.0);
    }

    @Test
    public void shouldCreateACompleteInvoice() {
//       ASSERT
        assertEquals(inputInvoice, outputInvoice);
    }

    @Test
    public void shouldReturnTheNameIfTheCustomer() {
//        ARRANGE
        String expectedCustomerName = "Uziel";
//        ACT
        outputInvoice.setName("Uziel");
        String actualCustomerName = outputInvoice.getName();
//        ASSERT
        assertEquals(expectedCustomerName, actualCustomerName);
    }

}