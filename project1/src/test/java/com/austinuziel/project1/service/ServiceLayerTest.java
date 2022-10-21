package com.austinuziel.project1.service;

import com.austinuziel.project1.controllers.GameController;
import com.austinuziel.project1.models.*;
import com.austinuziel.project1.repositories.*;
import com.austinuziel.project1.services.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class ServiceLayerTest {

    private GameRepo gameRepo;
    private ConsoleRepo consoleRepo;
    private TShirtRepo tShirtRepo;
    private ServiceLayer serviceLayer;
    private SalesTaxRateRepo salesTaxRateRepo;
    private ProcessingFeeRepo processingFeeRepo;
    private InvoiceRepo invoiceRepo;
    private Game inputGame;
    private Console inputConsole;
    private TShirt inputTShirt;
    private Invoice inputInvoice;
    private Invoice outputInvoice;
    private Invoice inputInvoice2;
    private Invoice outputInvoice2;
    private Invoice inputInvoice3;
    private Invoice outputInvoice3;
    private Invoice finalInvoice;
    private Invoice finalInvoice2;
    private Invoice finalInvoice3;


    @Before
    public void setUp() throws Exception {
        setupGameRepoMock();
        setupConsoleRepoMock();
        setupTShirtRepoMock();
        setupProcessingFeeRepoMock();
        setupTaxRateRepoMock();
        setupInvoiceRepoMock();


        serviceLayer = new ServiceLayer(gameRepo, consoleRepo, tShirtRepo, invoiceRepo, salesTaxRateRepo, processingFeeRepo);

    }

    @Test
    public void doSomething() {
        System.out.println("This is here so my before runs.");
    }

    private void setupGameRepoMock() {
        gameRepo = mock(GameRepo.class);
        inputGame = new Game();
        inputGame.setQuantity(50);
        inputGame.setPrice(20.00);
        inputGame.setDescription("Hockey Game");
        inputGame.setStudio("EA Sports");
        inputGame.setTitle("NHL 2022");
        inputGame.setEsrbRating("M");
        inputGame.setGameId(1);

        Optional<Game> optional = Optional.of(inputGame);

        doReturn(optional).when(gameRepo).findById(1);

        Game output = new Game();
        output.setQuantity(40);
        output.setPrice(20.00);
        output.setDescription("Hockey Game");
        output.setStudio("EA Sports");
        output.setTitle("NHL 2022");
        output.setEsrbRating("M");
        output.setGameId(1);

        doReturn(output).when(gameRepo).save(inputGame);
    }

    private void setupConsoleRepoMock() {
        consoleRepo = mock(ConsoleRepo.class);
        inputConsole = new Console();
        inputConsole.setPrice(150.00);
        inputConsole.setQuantity(100);
        inputConsole.setMemoryAmount("200 Gigabytes");
        inputConsole.setProcessor("I8");
        inputConsole.setModel("XBOX One");
        inputConsole.setManufacturer("Microsoft");
        inputConsole.setConsoleId(1);

        Optional<Console> optional = Optional.of(inputConsole);

        doReturn(optional).when(consoleRepo).findById(1);

        Console output = new Console();
        output = new Console();
        output.setPrice(150.00);
        output.setQuantity(99);
        output.setMemoryAmount("200 Gigabytes");
        output.setProcessor("I8");
        output.setModel("XBOX One");
        output.setManufacturer("Microsoft");
        output.setConsoleId(1);

        doReturn(output).when(consoleRepo).save(inputConsole);

    }

    private void setupTShirtRepoMock() {
        tShirtRepo = mock(TShirtRepo.class);
        inputTShirt = new TShirt();
        inputTShirt.setQuantity(15);
        inputTShirt.setPrice(20.00);
        inputTShirt.setDescription("Black 2X TShirt with cool design");
        inputTShirt.setColor("Black");
        inputTShirt.setSize("2X");
        inputTShirt.setTShirtId(1);

        Optional<TShirt> optional = Optional.of(inputTShirt);

        doReturn(optional).when(tShirtRepo).findById(1);

        TShirt output = new TShirt();
        output.setQuantity(5);
        output.setPrice(20.00);
        output.setDescription("Black 2X TShirt with cool design");
        output.setColor("Black");
        output.setSize("2X");
        output.setTShirtId(1);

        doReturn(output).when(tShirtRepo).save(inputTShirt);

    }

    private void setupProcessingFeeRepoMock() {
        processingFeeRepo = mock(ProcessingFeeRepo.class);
        ProcessingFee output = new ProcessingFee();
        output.setProductType("Game");
        output.setFee(1.49);
        ProcessingFee output2 = new ProcessingFee();
        output2.setProductType("TShirt");
        output2.setFee(1.98);
        ProcessingFee output3 = new ProcessingFee();
        output3.setProductType("Console");
        output3.setFee(14.99);
        Optional<ProcessingFee> optional = Optional.of(output);
        Optional<ProcessingFee> optional2 = Optional.of(output2);
        Optional<ProcessingFee> optional3 = Optional.of(output3);

        doReturn(optional).when(processingFeeRepo).findByProductType("Game");
        doReturn(optional2).when(processingFeeRepo).findByProductType("TShirt");
        doReturn(optional3).when(processingFeeRepo).findByProductType("Console");
    }

    private void setupTaxRateRepoMock() {
        salesTaxRateRepo = mock(SalesTaxRateRepo.class);
        SalesTaxRate output = new SalesTaxRate();
        output.setState("MD");
        output.setRate(0.07);
        Optional<SalesTaxRate> optional = Optional.of(output);
        doReturn(optional).when(salesTaxRateRepo).findByState("MD");
    }

    private void setupInvoiceRepoMock() {
        invoiceRepo = mock(InvoiceRepo.class);
        inputInvoice = new Invoice();
        inputInvoice.setName("Uziel");
        inputInvoice.setStreet("123 ST MAIN");
        inputInvoice.setCity("Dallas");
        inputInvoice.setState("MD");
        inputInvoice.setZipcode("12345");
        inputInvoice.setItemType("Game");
        inputInvoice.setItemId(1);
        inputInvoice.setQuantity(10);

        finalInvoice = new Invoice();
        finalInvoice.setName("Uziel");
        finalInvoice.setStreet("123 ST MAIN");
        finalInvoice.setCity("Dallas");
        finalInvoice.setState("MD");
        finalInvoice.setZipcode("12345");
        finalInvoice.setItemType("Game");
        finalInvoice.setItemId(1);
        finalInvoice.setQuantity(10);
        finalInvoice.setUnitPrice(20.0);
        finalInvoice.setSubtotal(200.0);
        finalInvoice.setTax(new BigDecimal("14.00"));
        finalInvoice.setProcessingFee(1.49);
        finalInvoice.setTotal(215.49);

        outputInvoice = new Invoice();
        outputInvoice.setName("Uziel");
        outputInvoice.setStreet("123 ST MAIN");
        outputInvoice.setCity("Dallas");
        outputInvoice.setState("MD");
        outputInvoice.setZipcode("12345");
        outputInvoice.setItemType("Game");
        outputInvoice.setItemId(1);
        outputInvoice.setQuantity(10);
        outputInvoice.setUnitPrice(20.0);
        outputInvoice.setSubtotal(200.0);
        outputInvoice.setTax(new BigDecimal("14.00"));
        outputInvoice.setProcessingFee(1.49);
        outputInvoice.setTotal(215.49);
        outputInvoice.setInvoiceId(1);

        inputInvoice2 = new Invoice();
        inputInvoice2.setName("Uziel");
        inputInvoice2.setStreet("123 ST MAIN");
        inputInvoice2.setCity("Dallas");
        inputInvoice2.setState("MD");
        inputInvoice2.setZipcode("12345");
        inputInvoice2.setItemType("TShirt");
        inputInvoice2.setItemId(1);
        inputInvoice2.setQuantity(10);

        finalInvoice2 = new Invoice();
        finalInvoice2.setName("Uziel");
        finalInvoice2.setStreet("123 ST MAIN");
        finalInvoice2.setCity("Dallas");
        finalInvoice2.setState("MD");
        finalInvoice2.setZipcode("12345");
        finalInvoice2.setItemType("TShirt");
        finalInvoice2.setItemId(1);
        finalInvoice2.setQuantity(10);
        finalInvoice2.setUnitPrice(20.0);
        finalInvoice2.setSubtotal(200.0);
        finalInvoice2.setTax(new BigDecimal("14.00"));
        finalInvoice2.setProcessingFee(1.98);
        finalInvoice2.setTotal(215.98);

        outputInvoice2 = new Invoice();
        outputInvoice2.setName("Uziel");
        outputInvoice2.setStreet("123 ST MAIN");
        outputInvoice2.setCity("Dallas");
        outputInvoice2.setState("MD");
        outputInvoice2.setZipcode("12345");
        outputInvoice2.setItemType("TShirt");
        outputInvoice2.setItemId(1);
        outputInvoice2.setQuantity(10);
        outputInvoice2.setUnitPrice(20.0);
        outputInvoice2.setSubtotal(200.0);
        outputInvoice2.setTax(new BigDecimal("14.00"));
        outputInvoice2.setProcessingFee(1.98);
        outputInvoice2.setTotal(215.98);
        outputInvoice2.setInvoiceId(2);

        inputInvoice3 = new Invoice();
        inputInvoice3.setName("Uziel");
        inputInvoice3.setStreet("123 ST MAIN");
        inputInvoice3.setCity("Dallas");
        inputInvoice3.setState("MD");
        inputInvoice3.setZipcode("12345");
        inputInvoice3.setItemType("Console");
        inputInvoice3.setItemId(1);
        inputInvoice3.setQuantity(1);

        finalInvoice3 = new Invoice();
        finalInvoice3.setName("Uziel");
        finalInvoice3.setStreet("123 ST MAIN");
        finalInvoice3.setCity("Dallas");
        finalInvoice3.setState("MD");
        finalInvoice3.setZipcode("12345");
        finalInvoice3.setItemType("Console");
        finalInvoice3.setItemId(1);
        finalInvoice3.setQuantity(1);
        finalInvoice3.setUnitPrice(150.0);
        finalInvoice3.setSubtotal(150.0);
        finalInvoice3.setTax(new BigDecimal("10.50"));
        finalInvoice3.setProcessingFee(14.99);
        finalInvoice3.setTotal(175.49);

        outputInvoice3 = new Invoice();
        outputInvoice3.setName("Uziel");
        outputInvoice3.setStreet("123 ST MAIN");
        outputInvoice3.setCity("Dallas");
        outputInvoice3.setState("MD");
        outputInvoice3.setZipcode("12345");
        outputInvoice3.setItemType("Console");
        outputInvoice3.setItemId(1);
        outputInvoice3.setQuantity(1);
        outputInvoice3.setUnitPrice(150.0);
        outputInvoice3.setSubtotal(150.0);
        outputInvoice3.setTax(new BigDecimal("10.50"));
        outputInvoice3.setProcessingFee(14.99);
        outputInvoice3.setTotal(175.49);
        outputInvoice3.setInvoiceId(3);

        doReturn(outputInvoice).when(invoiceRepo).save(finalInvoice);
        doReturn(outputInvoice2).when(invoiceRepo).save(finalInvoice2);
        doReturn(outputInvoice3).when(invoiceRepo).save(finalInvoice3);

    }

    @Test
    public void shouldReturnFullInvoice() throws Exception {
        Invoice actualReturn = serviceLayer.buildInvoice(inputInvoice);
        assertEquals(outputInvoice, actualReturn);
        Invoice actualReturn2 = serviceLayer.buildInvoice(inputInvoice2);
        assertEquals(outputInvoice2, actualReturn2);
        Invoice actualReturn3 = serviceLayer.buildInvoice(inputInvoice3);
        assertEquals(outputInvoice3, actualReturn3);
    }
}
