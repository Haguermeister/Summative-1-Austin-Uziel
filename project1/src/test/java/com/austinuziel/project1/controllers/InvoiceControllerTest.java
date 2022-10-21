package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Invoice;
import com.austinuziel.project1.repositories.InvoiceRepo;
import com.austinuziel.project1.services.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    ServiceLayer serviceLayer;
    @MockBean
    InvoiceRepo invoiceRepo;

    private String inputInvoiceJSON;
    private String outputInvoiceJSON;
    private String returnListJSON;
    private Invoice inputInvoice;
    private Invoice inputInvoice2;
    private Invoice finalInvoice;
    private Invoice outputInvoice;
    private Optional<Invoice> optional;


    @Before
    public void setUp() throws Exception {

        inputInvoice = new Invoice();
        inputInvoice.setName("Uziel");
        inputInvoice.setStreet("123 ST MAIN");
        inputInvoice.setCity("Dallas");
        inputInvoice.setState("MD");
        inputInvoice.setZipcode("12345");
        inputInvoice.setItemType("Game");
        inputInvoice.setItemId(1);
        inputInvoice.setQuantity(10);

        inputInvoice2 = new Invoice();
        inputInvoice2.setName("Uziel");
        inputInvoice2.setStreet("123 ST MAIN");
        inputInvoice2.setCity("Dallas");
        inputInvoice2.setState("MD");
        inputInvoice2.setZipcode("12345");
        inputInvoice2.setItemType("Game");
        inputInvoice2.setItemId(2);
        inputInvoice2.setQuantity(10);

        finalInvoice = new Invoice();
        finalInvoice.setName("Uziel");
        finalInvoice.setStreet("123 ST MAIN");
        finalInvoice.setCity("Dallas");
        finalInvoice.setState("MD");
        finalInvoice.setZipcode("12345");
        finalInvoice.setItemType("Game");
        finalInvoice.setItemId(1);
        finalInvoice.setQuantity(10);
        finalInvoice.setUnitPrice(20.0);
        finalInvoice.setSubtotal(200.0);
        finalInvoice.setTax(new BigDecimal("14.00"));
        finalInvoice.setProcessingFee(1.49);
        finalInvoice.setTotal(215.49);
        finalInvoice.setInvoiceId(1);

        outputInvoice = new Invoice();
        outputInvoice.setName("Uziel");
        outputInvoice.setStreet("123 ST MAIN");
        outputInvoice.setCity("Dallas");
        outputInvoice.setState("MD");
        outputInvoice.setZipcode("12345");
        outputInvoice.setItemType("Game");
        outputInvoice.setItemId(1);
        outputInvoice.setQuantity(10);
        outputInvoice.setUnitPrice(20.0);
        outputInvoice.setSubtotal(200.0);
        outputInvoice.setTax(new BigDecimal("14.00"));
        outputInvoice.setProcessingFee(1.49);
        outputInvoice.setTotal(215.49);
        outputInvoice.setInvoiceId(1);

        List<Invoice> returnList = new ArrayList<>(Arrays.asList(outputInvoice, outputInvoice));

        inputInvoiceJSON = mapper.writeValueAsString(inputInvoice);
        outputInvoiceJSON = mapper.writeValueAsString(outputInvoice);
        returnListJSON = mapper.writeValueAsString(returnList);
        optional = Optional.of(outputInvoice);

        doReturn(optional).when(invoiceRepo).findById(1);
        doReturn(returnList).when(invoiceRepo).findAll();
        doReturn(outputInvoice).when(serviceLayer).buildInvoice(inputInvoice);
        doThrow(new NullPointerException("All values needed")).when(serviceLayer).buildInvoice(inputInvoice2);
        doReturn(outputInvoice).when(invoiceRepo).save(finalInvoice);
    }

    @Test
    public void shouldReturnAllInvoices() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/invoice"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(content().json(returnListJSON));
    }

    @Test
    public void shouldReturnInvoice() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/invoice/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(outputInvoiceJSON));
    }

    @Test
    public void shouldCreateInvoice() throws Exception {
        // Arrange and Act
        mockMvc.perform(post("/invoice")
                        .content(inputInvoiceJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json(outputInvoiceJSON));
    }

    @Test
    public void shouldDeleteInvoice() throws Exception {
        // Arrange and Act
        mockMvc.perform(delete("/invoice/1"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(invoiceRepo).deleteById(1);
    }

    @Test
    public void shouldRespondWith422WhenValueIsNull() throws Exception {
        inputInvoice2.setQuantity(null);
        inputInvoice2.setState(null);
        inputInvoiceJSON = mapper.writeValueAsString(inputInvoice2);

        mockMvc.perform(post("/invoice")
                        .content(inputInvoiceJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}