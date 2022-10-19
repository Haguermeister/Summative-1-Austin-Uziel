package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Invoice;
import com.austinuziel.project1.services.InvoiceService;
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

    private List<Invoice> allInvoices2;

    private Invoice inputInvoice;
    private Invoice outputInvoice;
    private Invoice outputInvoice2;
    private Invoice outputInvoice3;
    private Invoice outputInvoice4;
    private Invoice inputInvoiceBadRequest;

    private String inputJson;
    private String outputJson;
    private String outputJson2;
    private String allInvoicesJSONFormat;
    private String allInvoicesJSONFormat2;
    private String inputJsonBadRequest;

    @Before
    public void setUp() throws Exception {
        inputInvoice = new Invoice(
                "Uziel", "123 ST MAIN", "Dallas", "TX", 12345, "Game Console", 1,
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

        outputInvoice3 = new Invoice(
                3, "Uzi", "123 ST MAIN", "Dallas", "TX", 12345, "Game Console", 1,
                4, 5.99F, 23.96F, 6.9F, 5.99F, 3682L
        );
        outputInvoice4 = new Invoice(
                4, "Uzi", "123 ST MAIN", "Dallas", "MT", 12345, "Game", 2,
                7, 5.99F, 23.96F, 6.9F, 5.99F, 2121L
        );
        inputInvoiceBadRequest = new Invoice();
        inputInvoiceBadRequest.setStreet("123 ST MAIN");
        inputInvoiceBadRequest.setCity("Dallas");
        inputInvoiceBadRequest.setState("TX");
        inputInvoiceBadRequest.setZipCode(12345);
        inputInvoiceBadRequest.setItemType("Game Console");
        inputInvoiceBadRequest.setTax(5.99F);
        inputInvoiceBadRequest.setQuantity(4);
        inputInvoiceBadRequest.setItemId(2);
        inputInvoiceBadRequest.setProcessingFee(6.9F);
        inputInvoiceBadRequest.setUnit_price(5.99F);
        inputInvoiceBadRequest.setSubtotal(23.96F);
        inputInvoiceBadRequest.setTotal(36L);

        inputJson = mapper.writeValueAsString(inputInvoice);
        inputJsonBadRequest = mapper.writeValueAsString(inputInvoiceBadRequest);
        outputJson = mapper.writeValueAsString(outputInvoice);
        outputJson2 = mapper.writeValueAsString(outputInvoice2);

        List<Invoice> allInvoices = new ArrayList<>(Arrays.asList(outputInvoice, outputInvoice2));
        allInvoices2 = new ArrayList<>(Arrays.asList(outputInvoice3, outputInvoice4));
        allInvoicesJSONFormat = mapper.writeValueAsString(allInvoices);
        allInvoicesJSONFormat2 = mapper.writeValueAsString(allInvoices2);

        doReturn(outputInvoice).when(invoiceService).createNewInvoice(inputInvoice);
        doReturn(allInvoices).when(invoiceService).getAllInvoices();
        doReturn(allInvoices2).when(invoiceService).getInvoiceByName("Uzi");
        doReturn(Optional.of(outputInvoice)).when(invoiceService).getInvoiceById(1);
        doNothing().when(invoiceService).deleteInvoiceById(1);


    }

    //    POST ENDPOINTS
    @Test
    public void shouldReturnANewInvoiceInPostRequest() throws Exception {
        Invoice invoiceTest = new Invoice();
        invoiceTest.setId(1000);
        invoiceTest.setName("Uz");
        invoiceTest.setStreet("123 ST MAIN");
        invoiceTest.setCity("Dallas");
        invoiceTest.setState("TX");
        invoiceTest.setZipCode(12345);
        invoiceTest.setItemType("Game Console");
        invoiceTest.setTax(5.99F);
        invoiceTest.setQuantity(4);
        invoiceTest.setItemId(1000);
        invoiceTest.setProcessingFee(6.9F);
        invoiceTest.setUnit_price(5.99F);
        invoiceTest.setSubtotal(23.96F);
        invoiceTest.setTotal(36L);

        String inputJson = mapper.writeValueAsString(invoiceTest);
        String outputJson = mapper.writeValueAsString(invoiceTest);

        doReturn(invoiceTest).when(invoiceService).createNewInvoice(invoiceTest);
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

    @Test
    public void shouldReturnA422StatusCodeOnUnSuccessfulPostRequest() throws Exception {

        // Arrange and Act
        mockMvc.perform(post("/invoice")
                        .content(inputJsonBadRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    //    PUT ENDPOINTS
    @Test
    public void shouldReturn422HttpStatusCodeOnUnSuccessfulResponseOnPut() throws Exception {

        // Arrange and Act
        mockMvc.perform(put("/invoice")       // Act
                        .content(inputJsonBadRequest)                     // Act
                        .contentType(MediaType.APPLICATION_JSON)    // Act
                ).andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

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
        mockMvc.perform(get("/invoice/getById/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
        ;
    }

    @Test
    public void shouldReturnAListOfInvoiceByName() throws Exception {
        mockMvc.perform(get("/invoice/getByName/Uzi"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allInvoicesJSONFormat2));
    }


    @Test
    public void shouldReturn200StatusCodeWhenGettingAnInvoiceById() throws Exception {

        doReturn(Optional.of(outputInvoice)).when(invoiceService).getInvoiceById(1);
        mockMvc.perform(get("/invoice", 1))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    DELETE ENDPOINTS

    @Test
    public void shouldRespondWithA204CodeStatusWhenDeletingAnInvoiceById() throws Exception {
        mockMvc.perform(delete("/invoice/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


}