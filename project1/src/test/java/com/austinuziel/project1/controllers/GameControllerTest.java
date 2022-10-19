package com.austinuziel.project1.controllers;

import com.austinuziel.project1.models.Game;
import com.austinuziel.project1.repositories.GameRepo;
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

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @MockBean
    GameRepo gameRepo;

    private String inputGameJSON;
    private String outputGameJSON;
    private String inputGame2JSON;
    private String outputGame2JSON;
    private String returnListJSON;

    @Before
    public void setUp() throws Exception {
        Game inputGame = new Game();
        inputGame.setQuantity(50);
        inputGame.setPrice(20.00);
        inputGame.setDescription("Hockey Game");
        inputGame.setStudio("EA Sports");
        inputGame.setTitle("NHL 2022");
        inputGame.setEsrbRating("M");

        Game inputGame2 = new Game();
        inputGame2.setQuantity(40);
        inputGame2.setPrice(10.00);
        inputGame2.setDescription("Football Game");
        inputGame2.setStudio("EA Sports");
        inputGame2.setTitle("NCAA 2022");
        inputGame2.setEsrbRating("M");
        inputGame2.setGameId(1);


        Game outputGame = new Game(20.00, 50, 1, "M", "EA Sports", "NHL 2022", "Hockey Game");

        Game outputGame2 = new Game();
        outputGame2.setQuantity(40);
        outputGame2.setPrice(10.00);
        outputGame2.setDescription("Football Game");
        outputGame2.setStudio("EA Sports");
        outputGame2.setTitle("NCAA 2022");
        outputGame2.setEsrbRating("M");
        outputGame2.setGameId(1);

        inputGameJSON = mapper.writeValueAsString(inputGame);
        outputGameJSON = mapper.writeValueAsString(outputGame);

        inputGame2JSON = mapper.writeValueAsString(inputGame2);
        outputGame2JSON = mapper.writeValueAsString(outputGame2);

        List<Game> returnList = new ArrayList<>(Arrays.asList(outputGame, outputGame2));
        returnListJSON = mapper.writeValueAsString(returnList);

        Optional<Game> optional = Optional.of(outputGame);

        doReturn(optional).when(gameRepo).findByTitle("NHL 2022");
        doReturn(optional).when(gameRepo).findById(1);
        doReturn(returnList).when(gameRepo).findAll();
        doReturn(returnList).when(gameRepo).findByStudio("EA Sports");
        doReturn(returnList).when(gameRepo).findByEsrbRating("M");
        doReturn(outputGame).when(gameRepo).save(inputGame);
        doReturn(outputGame2).when(gameRepo).save(inputGame2);
    }

    @Test
    public void shouldReturnAllGames() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/game"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty())
                .andExpect(content().json(returnListJSON));
    }

    @Test
    public void shouldReturnGameById() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/game/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(outputGameJSON));
    }

    @Test
    public void shouldReturnGamesByStudio() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/game/studio/EA Sports"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(returnListJSON));
    }

    @Test
    public void shouldReturnGamesByEsrb() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/game/esrb/M"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(returnListJSON));
    }

    @Test
    public void shouldReturnGamesByTitle() throws Exception {
        // Arrange and Act
        mockMvc.perform(get("/game/title/NHL 2022"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(content().json(outputGameJSON));
    }

    @Test
    public void shouldCreateNewGame() throws Exception {
        // Arrange and Act
        mockMvc.perform(post("/game")
                        .content(inputGameJSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(content().json(outputGameJSON));
    }

    @Test
    public void shouldUpdateGame() throws Exception {
        // Arrange and Act
        Game inputGame2 = new Game();
        inputGame2.setQuantity(40);
        inputGame2.setPrice(10.00);
        inputGame2.setDescription("Football Game");
        inputGame2.setStudio("EA Sports");
        inputGame2.setTitle("NCAA 2022");
        inputGame2.setEsrbRating("M");
        inputGame2.setGameId(1);

        mockMvc.perform(put("/game")
                        .content(inputGame2JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(gameRepo).save(inputGame2);
    }

    @Test
    public void shouldDeleteGame() throws Exception {
        // Arrange and Act
        mockMvc.perform(delete("/game/2"))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(gameRepo).deleteById(2);
    }
}
