package com.austinuziel.project1.services;

import com.austinuziel.project1.models.*;
import com.austinuziel.project1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class ServiceLayer {
    private GameRepo gameRepo;
    private ConsoleRepo consoleRepo;
    private TShirtRepo tShirtRepo;
    private InvoiceRepo invoiceRepo;
    private SalesTaxRateRepo salesTaxRateRepo

    @Autowired
    public ServiceLayer(GameRepo gameRepo, ConsoleRepo consoleRepo, TShirtRepo tShirtRepo, InvoiceRepo invoiceRepo, SalesTaxRateRepo salesTaxRateRepo) {
        this.gameRepo = gameRepo;
        this.consoleRepo = consoleRepo;
        this.tShirtRepo = tShirtRepo;
        this.invoiceRepo = invoiceRepo;
        this.salesTaxRateRepo = salesTaxRateRepo;
    }

    private Map<Double,Integer> checkInventory(String gameType, Integer itemId) {
        switch (gameType) {
            case "Game":
                Optional<Game> optionalGame = gameRepo.findById(itemId);
                if (optionalGame.isPresent()) {
                    Map<Double,Integer> returnMap = new HashMap<>(optionalGame.get().getPrice(),optionalGame.get().getQuantity()) ;

                } else {
                    return null;
                }
            case "TShirt":
                Optional<TShirt> optionalTShirt = tShirtRepo.findById(itemId);
                if (optionalTShirt.isPresent()) {
                    return new HashMap<>(optionalTShirt.get().getPrice().intValue(),optionalTShirt.get().getQuantity()) ;
                } else {
                    return null;
                }
            case "Console":
                Optional<Console> optionalConsole = consoleRepo.findById(itemId);
                if (optionalConsole.isPresent()) {
                    return new HashMap<>(optionalConsole.get().getPrice().intValue(),optionalConsole.get().getQuantity()) ;
                } else {
                    return null;
                }
            default: // Is not a Game Console or TShirt
                return null;
        }
    }

    private Invoice buildInvoice(Invoice invoice) {
        Optional<SalesTaxRate> optional = salesTaxRateRepo.findByState(invoice.getState());
        if (invoice.getQuantity() > 0 && optional.isPresent()) {
            Map checkI = checkInventory(invoice.getItemType(), invoice.getQuantity())
            // check inventory
            if (checkI. != null && checkI >= invoice.getQuantity()) {
                // Do calculations
                Integer subtotal = invoice.getQuantity() *
            } else {
                throw new RuntimeException();
            }

        }
        return null;
    }
}
