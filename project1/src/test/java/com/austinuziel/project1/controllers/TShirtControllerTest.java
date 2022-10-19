package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.TShirt;
import com.austinuziel.project1.repositories.TShirtRepo;
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

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    TShirtRepo tShirtRepo;

    private String inputTShirtJSON;
    private String outputTShirtJSON;
    private String inputTShirt2JSON;
    private String outputTShirt2JSON;
    private String returnListJSON;
    private TShirt inputTShirt;
    private TShirt inputTShirt2;
    private TShirt inputTShirt3;
    private String inputTShirt3JSON;
    private String returnSize;


    @Before
    public void setUp() throws Exception {
        inputTShirt = new TShirt();
        inputTShirt.setQuantity(15);
        inputTShirt.setPrice(20.00);
        inputTShirt.setDescription("Black 2X TShirt with cool design");
        inputTShirt.setColor("Black");
        inputTShirt.setSize("2X");
        inputTShirt.setTShirtId(2);

        inputTShirt2 = new TShirt();
        inputTShirt2.setQuantity(40);
        inputTShirt2.setPrice(10.00);
        inputTShirt2.setDescription("Cool Shirt");
        inputTShirt2.setColor("Black");
        inputTShirt2.setSize("XL");

        inputTShirt3 = new TShirt();

        TShirt outputTShirt = new TShirt(20.00, 15, 1,"2X", "Black", "Black 2X TShirt with cool design");

        TShirt outputTShirt2 = new TShirt();
        outputTShirt2.setQuantity(40);
        outputTShirt2.setPrice(10.00);
        outputTShirt2.setDescription("Football Game");
        outputTShirt2.setColor("Black");
        outputTShirt2.setSize("XL");
        outputTShirt2.setTShirtId(1);

        inputTShirtJSON = mapper.writeValueAsString(inputTShirt);
        outputTShirtJSON = mapper.writeValueAsString(outputTShirt);

        inputTShirt2JSON = mapper.writeValueAsString(inputTShirt2);
        outputTShirt2JSON = mapper.writeValueAsString(outputTShirt2);

        List<TShirt> returnList = new ArrayList<>(Arrays.asList(outputTShirt, outputTShirt2));
        returnListJSON = mapper.writeValueAsString(returnList);

        List<TShirt> returnSizeList = new ArrayList<>(Arrays.asList(outputTShirt));
        returnSize  = mapper.writeValueAsString(returnSizeList);

        Optional<TShirt> optional = Optional.of(outputTShirt);

        doReturn(optional).when(tShirtRepo).findById(1);
        doReturn(returnList).when(tShirtRepo).findAll();
        doReturn(returnList).when(tShirtRepo).findByColor("Black");
        doReturn(returnSizeList).when(tShirtRepo).findBySize("2X");
        doReturn(outputTShirt).when(tShirtRepo).save(inputTShirt);
        doReturn(outputTShirt2).when(tShirtRepo).save(inputTShirt2);
    }

    @Test
    public void shouldReturnAllTShirts() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/tshirt"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(content().json(returnListJSON));
    }

    @Test
    public void shouldReturnTShirtById() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/tshirt/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(outputTShirtJSON));
    }

    @Test
    public void shouldReturnTShirtsByColor() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/tshirt/color/Black"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(returnListJSON));
    }

    @Test
    public void shouldReturnTShirtsBySize() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/tshirt/size/2X"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(content().json(returnSize));
    }

    @Test
    public void shouldCreateTShirt() throws Exception {
        // Arrange and Act
        mockMvc.perform(post("/tshirt")
                        .content(inputTShirtJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json(outputTShirtJSON));
    }

    @Test
    public void shouldUpdateTShirt() throws Exception {
        // Arrange and Act

        inputTShirt2.setTShirtId(1);
        inputTShirt2JSON = mapper.writeValueAsString(inputTShirt2);

        mockMvc.perform(put("/tshirt")
                        .content(inputTShirt2JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(tShirtRepo).save(inputTShirt2);
    }

    @Test
    public void shouldDeleteTShirt() throws Exception {
        // Arrange and Act
        mockMvc.perform(delete("/tshirt/2"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(tShirtRepo).deleteById(2);
    }
    @Test
    public void shouldRespondWith422WhenValueIsNull() throws Exception {
        inputTShirt3JSON = mapper.writeValueAsString(inputTShirt3);

        mockMvc.perform(post("/tshirt")
                        .content(inputTShirt3JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}