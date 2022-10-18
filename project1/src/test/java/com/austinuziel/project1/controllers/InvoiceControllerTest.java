package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Game;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
    private Invoice outputInvoice2;

    String inputJson;
    String outputJson;
    String outputJson2;
    String allInvoicesJSONFormat;

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
        outputInvoice2 = new Invoice(
                2, "John", "123 ST MAIN", "Dallas", "MT", 12345, "Game", 2,
                7, 5.99F, 23.96F, 6.9F, 5.99F, 2121L
        );

        inputJson = mapper.writeValueAsString(inputInvoice);
        outputJson = mapper.writeValueAsString(outputInvoice);
        outputJson2 = mapper.writeValueAsString(outputInvoice2);

        List<Invoice> allInvoices = new ArrayList<>(Arrays.asList(outputInvoice,outputInvoice2));
        allInvoicesJSONFormat = mapper.writeValueAsString(allInvoices);

        doReturn(outputInvoice).when(invoiceService).createNewInvoice(inputInvoice);
        doReturn(allInvoices).when(invoiceService).getAllInvoices();

        doNothing().when(invoiceService).deleteInvoiceById(1);


    }

    //    POST ENDPOINTS
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

    //    PUT ENDPOINTS
    @Test
    public void shouldGet204HttpResponseOnPut() throws Exception {

        // Arrange and Act
        mockMvc.perform(put("/invoice")       // Act
                        .content(inputJson)                     // Act
                        .contentType(MediaType.APPLICATION_JSON)    // Act
                ).andDo(print())
                .andExpect(status().isNoContent());
    }

    //    GET ENDPOINTS
    @Test
    public void shouldReturnAllInvoicesInCollection() throws Exception {
        mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty());
    }

    @Test
    public void shouldReturnA200StatusCodeWhenGettingAllInvoices() throws Exception {
        mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnOneInvoiceById() throws Exception {
        mockMvc.perform(get("/invoice"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty());
    }

    @Test
    public void shouldReturn200StatusCodeWhenGettingAnInvoiceById() throws Exception {

        doReturn(Optional.of(outputInvoice)).when(invoiceService).getInvoiceById(1);
        mockMvc.perform(get("/invoice", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    public void shouldReturnEmptyResponseWhenInvoiceNotFound() throws Exception {

        doReturn(Optional.empty()).when(invoiceService).getInvoiceById(100);
        mockMvc.perform(get("/invoice/100"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").doesNotExist())
        ;
    }

//    DELETE ENDPOINTS

    @Test
    public void shouldRespondWithA204CodeStatusWhenDeletingAnInvoiceById() throws Exception {
        mockMvc.perform(delete("/invoice/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


}