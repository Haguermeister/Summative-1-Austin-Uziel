package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Console;
import com.austinuziel.project1.repositories.ConsoleRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    ConsoleRepo consoleRepo;
    @SpyBean
    ConsoleRepo consoleRepoSneaky;

    private String inputGameConsoleJSON;
    private String outputGameConsoleJSON;
    private String inputGameConsole2JSON;
    private String outputGameConsole2JSON;
    private String returnListJSON;

    @Before
    public void setUp() throws Exception {
        Console inputConsole = new Console();
        inputConsole.setPrice(150.00);
        inputConsole.setQuantity(100);
        inputConsole.setMemoryAmount("200 Gigabytes");
        inputConsole.setProcessor("I8");
        inputConsole.setModel("XBOX One");
        inputConsole.setManufacturer("Microsoft");

        Console outputConsole = new Console();
        outputConsole.setPrice(150.00);
        outputConsole.setQuantity(100);
        outputConsole.setMemoryAmount("200 Gigabytes");
        outputConsole.setProcessor("I8");
        outputConsole.setModel("XBOX One");
        outputConsole.setManufacturer("Microsoft");
        outputConsole.setConsoleId(1);

        Console inputConsole2 = new Console();
        inputConsole2.setPrice(250.99);
        inputConsole2.setQuantity(200);
        inputConsole2.setMemoryAmount("1 Gigabytes");
        inputConsole2.setProcessor("I1");
        inputConsole2.setModel("XBOX");
        inputConsole2.setManufacturer("Microsoft");
        inputConsole2.setConsoleId(2);

        Console outputConsole2 = new Console();
        outputConsole2.setPrice(250.99);
        outputConsole2.setQuantity(200);
        outputConsole2.setMemoryAmount("1 Gigabytes");
        outputConsole2.setProcessor("I1");
        outputConsole2.setModel("XBOX");
        outputConsole2.setManufacturer("Microsoft");
        outputConsole2.setConsoleId(2);
        List<Console> outputList = new ArrayList<>(Arrays.asList(outputConsole, outputConsole2));
        inputGameConsoleJSON = mapper.writeValueAsString(inputConsole);
        outputGameConsoleJSON = mapper.writeValueAsString(outputConsole);
        inputGameConsole2JSON = mapper.writeValueAsString(inputConsole2);
        outputGameConsole2JSON = mapper.writeValueAsString(outputConsole2);
        returnListJSON = mapper.writeValueAsString(outputList);

        Optional<Console> optional = Optional.of(outputConsole);

        doReturn(outputList).when(consoleRepo).findByManufacturer("Microsoft");
        doReturn(outputList).when(consoleRepo).findAll();
        doReturn(optional).when(consoleRepo).findById(1);
        doReturn(outputConsole).when(consoleRepo).save(inputConsole);
    }

    @Test
    public void shouldReturnAllGameConsoles() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/gameConsole"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(content().json(returnListJSON));
    }

    @Test
    public void shouldReturnGameConsoleById() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/gameConsole/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(outputGameConsoleJSON));
    }

    @Test
    public void shouldReturnGamesByStudio() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/gameConsole/manufacturer/Microsoft"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(returnListJSON));
    }

    @Test
    public void shouldCreateNewGameConsole() throws Exception {
        // Arrange and Act
        mockMvc.perform(post("/gameConsole")
                        .content(inputGameConsoleJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json(outputGameConsoleJSON));
    }

    @Test
    public void shouldUpdateGameConsole() throws Exception {
        // Arrange and Act
        Console inputConsole2 = new Console();
        inputConsole2.setPrice(250.99);
        inputConsole2.setQuantity(200);
        inputConsole2.setMemoryAmount("1 Gigabytes");
        inputConsole2.setProcessor("I1");
        inputConsole2.setModel("XBOX");
        inputConsole2.setManufacturer("Microsoft");
        inputConsole2.setConsoleId(2);
        mockMvc.perform(put("/gameConsole")
                        .content(inputGameConsole2JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(consoleRepo).save(inputConsole2);
    }

    @Test
    public void shouldDeleteGameConsole() throws Exception {
        // Arrange and Act
        mockMvc.perform(delete("/gameConsole/2"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(consoleRepo).deleteById(2);
    }

}

