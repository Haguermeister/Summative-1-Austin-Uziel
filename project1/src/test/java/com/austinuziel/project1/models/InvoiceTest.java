package com.austinuziel.project1.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvoiceTest {

    Invoice inputInvoice;
    Invoice outputInvoice;

    @Before
    public void setUp() {
        inputInvoice = new Invoice(
                1, "Uziel", "123 ST MAIN", "Dallas", "TX", 12345, "Game Console", 1,
                4, 5.99F, 23.96F, 6.9F, 5.99F, 3682L
        );

        outputInvoice = new Invoice();
    }

    @Test
    public void shouldCreateACompleteInvoice() {
//        Arrange


//        ACT
        outputInvoice.setId(1);
        outputInvoice.setName("Uziel");
        outputInvoice.setStreet("123 ST MAIN");
        outputInvoice.setCity("Dallas");
        outputInvoice.setState("TX");
        outputInvoice.setZipCode(12345);
        outputInvoice.setItemType("Game Console");
        outputInvoice.setItemId(1);
        outputInvoice.setQuantity(4);
        outputInvoice.setUnit_price(5.99F);
        outputInvoice.setSubtotal(23.96F);
        outputInvoice.setTax(6.9F);
        outputInvoice.setProcessingFee(5.99F);
        outputInvoice.setTotal(3682L);

//        ASSERT

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