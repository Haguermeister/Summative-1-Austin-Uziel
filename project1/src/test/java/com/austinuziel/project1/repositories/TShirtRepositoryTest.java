package com.austinuziel.project1.repositories;
import com.austinuziel.project1.models.Game;
import com.austinuziel.project1.models.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtRepositoryTest {

    @Autowired
    TShirtRepo tShirtRepo;
    private TShirt tShirt;
    private TShirt tShirt2;
    private TShirt tShirt3;


    @Before
    public void setUp() throws Exception {
        tShirtRepo.deleteAll();
        tShirt = new TShirt();
        tShirt.setColor("red");
        tShirt.setDescription("An old t shirt");
        tShirt.setSize("Medium");
        tShirt.setQuantity(2);
        tShirt.setPrice(12.99);
        tShirt2 = new TShirt();
        tShirt2.setColor("blue");
        tShirt2.setDescription("A new t shirt");
        tShirt2.setSize("Large");
        tShirt2.setPrice(12.99);
        tShirt2.setQuantity(6);
        tShirt3 = new TShirt();
        tShirt3.setColor("blue");
        tShirt3.setDescription("A new t shirt");
        tShirt3.setSize("Large");
        tShirt3.setPrice(12.99);
        tShirt3.setQuantity(6);

    }


    @Test
    public void addGetDeleteTShirt() {
        tShirt = tShirtRepo.save(tShirt);
        Optional<TShirt> tShirt1 = tShirtRepo.findById(tShirt.getTShirtId());
        assertEquals(tShirt1.get(), tShirt);
        tShirtRepo.deleteById(tShirt.getTShirtId());
        tShirt1 = tShirtRepo.findById(tShirt.getTShirtId());
        assertFalse(tShirt1.isPresent());
    }

    @Test
    public void updateTShirt() {
        tShirt = tShirtRepo.save(tShirt);
        tShirt2 = tShirtRepo.save(tShirt2);
        Optional<TShirt> tshirtRet = tShirtRepo.findById(tShirt2.getTShirtId());
        assertEquals(tshirtRet.get(), tShirt2);
    }

    @Test
    public void getAllTShirts() {
        tShirt = tShirtRepo.save(tShirt);
        tShirt2 = tShirtRepo.save(tShirt2);
        List<TShirt> tShirtList = tShirtRepo.findAll();
        assertEquals(tShirtList.size(), 2);
    }

    @Test
    public void getTShirtsByColor() {
        tShirt = tShirtRepo.save(tShirt);
        tShirt2 = tShirtRepo.save(tShirt2);
        tShirt3 = tShirtRepo.save(tShirt3);

        List<TShirt> tShirtList = tShirtRepo.findByColor("blue");
        assertEquals(tShirtList.size(), 2);
    }

    @Test
    public void getTShirtsBySize() {
        tShirt = tShirtRepo.save(tShirt);
        tShirt2 = tShirtRepo.save(tShirt2);
        tShirt3 = tShirtRepo.save(tShirt3);

        List<TShirt> tShirtList = tShirtRepo.findBySize("Medium");
        assertEquals(tShirtList.size(), 1);
    }
}


