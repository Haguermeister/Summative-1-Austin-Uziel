package com.austinuziel.project1.repositories;

import com.austinuziel.project1.models.Console;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleRepositoryTest {


    @Autowired
    ConsoleRepo consoleRepo;
    private Console console;

    @Before
    public void setUp() throws Exception {
        consoleRepo.deleteAll();
        console = new Console();
        console.setManufacturer("Sony");
        console.setModel("PS5");
        console.setProcessor(("I8"));
        console.setPrice(200);
        console.setQuantity(20);
        console.setMemoryAmount(("200 Gigs"));
        console = consoleRepo.save(console);
    }

    @Test
    public void addGetDeleteConsole() {

        Optional<Console> console1 = consoleRepo.findById(console.getConsoleId());
        assertEquals(console1.get(), console);
        consoleRepo.deleteById(console.getConsoleId());
        console1 = consoleRepo.findById(console.getConsoleId());
        assertFalse(console1.isPresent());
    }

    @Test
    public void updateArtist() {

        console = consoleRepo.save(console);

        console.setManufacturer("Microsoft");
        console.setModel("XBOX One");
        console.setProcessor(("I12"));
        console.setPrice(300);
        console.setQuantity(30);
        console.setMemoryAmount(("20 Gigs"));

        consoleRepo.save(console);

        Optional<Console> console1 = consoleRepo.findById(console.getConsoleId());
        assertEquals(console1.get(), console);
    }

    @Test
    public void getAllArtists() {

        console = consoleRepo.save(console);

        console = new Console();
        console.setManufacturer("Microsoft");
        console.setModel("XBOX One");
        console.setProcessor(("I12"));
        console.setPrice(300);
        console.setQuantity(30);
        console.setMemoryAmount(("20 Gigs"));

        console = consoleRepo.save(console);

        List<Console> consoleList = consoleRepo.findAll();
        assertEquals(consoleList.size(), 2);

    }
    @Test
    public void getByManufacturer() {

        console = consoleRepo.save(console);

        console = new Console();
        console.setManufacturer("Microsoft");
        console.setModel("XBOX One");
        console.setProcessor(("I12"));
        console.setPrice(300);
        console.setQuantity(30);
        console.setMemoryAmount(("20 Gigs"));

        console = consoleRepo.save(console);

        List<Console> consoleList = consoleRepo.findByManufacturer("Sony");
        assertEquals(consoleList.size(), 1);
    }
}