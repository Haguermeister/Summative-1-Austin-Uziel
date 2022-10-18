package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Invoice;
import com.austinuziel.project1.repositories.InvoiceRepo;
import com.austinuziel.project1.services.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    private ObjectMapper mapper = new ObjectMapper();

    private Invoice inputInvoice;
    private Invoice outputInvoice;

    String inputJson;
    String outputJson;

    @Before
    public void setUp() throws Exception {
        inputInvoice = new Invoice(
                1, "Uziel", "123 ST MAIN", "Dallas", "TX", 12345, "Game Console", 1,
                4, 5.99F, 23.96F, 6.9F, 5.99F, 3682L
        );

        outputInvoice = new Invoice(
                1, "Uziel", "123 ST MAIN", "Dallas", "TX", 12345, "Game Console", 1,
                4, 5.99F, 23.96F, 6.9F, 5.99F, 3682L
        );

        inputJson = mapper.writeValueAsString(inputInvoice);
        outputJson = mapper.writeValueAsString(outputInvoice);

        doReturn(outputInvoice).when(invoiceService).createNewInvoice(inputInvoice);

    }

    @Test
    public void shouldReturnANewInvoiceInPostRequest() throws Exception {

        // Arrange and Act
        mockMvc.perform(post("/invoice")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturnA201StatusCodeOnSuccessfulPostRequest() throws Exception {

        // Arrange and Act
        mockMvc.perform(post("/invoice")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }




}