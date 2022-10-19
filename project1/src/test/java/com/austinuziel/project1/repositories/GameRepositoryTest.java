package com.austinuziel.project1.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import java.util.Optional;
import com.austinuziel.project1.models.Game;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    GameRepo gameRepo;
    private Game game;
    private Game game2;


    @Before
    public void setUp() throws Exception {
        gameRepo.deleteAll();
        game = new Game();
        game.setTitle("NHL");
        game.setEsrbRating("T");
        game.setStudio(("EA Sports"));
        game.setPrice(60.00);
        game.setQuantity(100);
        game.setDescription(("Slap the puck"));
        game2 = new Game();
        game2.setTitle("NCAA");
        game2.setEsrbRating("M");
        game2.setStudio(("EA Sports"));
        game2.setPrice(65.00);
        game2.setQuantity(85);
        game2.setDescription(("Throw the ball"));    }

    @Test
    public void addGetDeleteGame() {
        game = gameRepo.save(game);
        Optional<Game> game1 = gameRepo.findById(game.getGameId());
        assertEquals(game1.get(), game);
        gameRepo.deleteById(game.getGameId());
        game1 = gameRepo.findById(game.getGameId());
        assertFalse(game1.isPresent());
    }

    @Test
    public void updateGame() {
        game = gameRepo.save(game);
        game2.setGameId(1);
        game2 = gameRepo.save(game2);
        Optional<Game> gameRet = gameRepo.findById(game2.getGameId());
        assertEquals(gameRet.get(), game2);
    }

    @Test
    public void getAllGames() {
        game = gameRepo.save(game);
        game2 = gameRepo.save(game2);
        List<Game> gameList = gameRepo.findAll();
        assertEquals(gameList.size(), 2);
    }

    @Test
    public void getGamesByStudio() {
        game = gameRepo.save(game);
        game2 = gameRepo.save(game2);
        List<Game> gameList = gameRepo.findByStudio("EA Sports");
        assertEquals(gameList.size(), 2);
    }
    @Test
    public void getGamesByESRB() {
        game = gameRepo.save(game);
        game2 = gameRepo.save(game2);
        List<Game> gameList = gameRepo.findByStudio("EA Sports");
        assertEquals(gameList.size(), 2);
    }
    @Test
    public void getGameByTitle() {
        game = gameRepo.save(game);
        Optional<Game> optionalGame = gameRepo.findByTitle("NHL");
        assertEquals(optionalGame.get(),game);
    }
}