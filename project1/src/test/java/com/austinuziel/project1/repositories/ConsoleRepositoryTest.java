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
    private Console console2;

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
        console2 = new Console();
        console2.setManufacturer("Microsoft");
        console2.setModel("XBOX One");
        console2.setProcessor(("I12"));
        console2.setPrice(300);
        console2.setQuantity(30);
        console2.setMemoryAmount(("20 Gigs"));
    }

    @Test
    public void addGetDeleteConsole() {
        console = consoleRepo.save(console);
        Optional<Console> console1 = consoleRepo.findById(console.getConsoleId());
        assertEquals(console1.get(), console);
        consoleRepo.deleteById(console.getConsoleId());
        console1 = consoleRepo.findById(console.getConsoleId());
        assertFalse(console1.isPresent());
    }

    @Test
    public void updateConsole() {
        console = consoleRepo.save(console);
        console2.setConsoleId(1);
        console2 = consoleRepo.save(console2);
        Optional<Console> consoleRet = consoleRepo.findById(console2.getConsoleId());
        assertEquals(consoleRet.get(), console2);
    }

    @Test
    public void getAllConsoles() {
        console = consoleRepo.save(console);
        console2 = consoleRepo.save(console2);
        List<Console> consoleList = consoleRepo.findAll();
        assertEquals(consoleList.size(), 2);
    }
    @Test
    public void getConsolesByManufacturer() {
        console = consoleRepo.save(console);
        console2 = consoleRepo.save(console2);
        List<Console> consoleList = consoleRepo.findByManufacturer("Sony");
        assertEquals(consoleList.size(), 1);
    }
}