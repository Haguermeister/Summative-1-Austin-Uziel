package com.austinuziel.project1.services;

import com.austinuziel.project1.models.*;
import com.austinuziel.project1.repositories.*;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ServiceLayer {
    private GameRepo gameRepo;
    private ConsoleRepo consoleRepo;
    private TShirtRepo tShirtRepo;
    private InvoiceRepo invoiceRepo;
    private SalesTaxRateRepo salesTaxRateRepo;
    private ProcessingFeeRepo processingFeeRepo;


    @Autowired
    public ServiceLayer(GameRepo gameRepo, ConsoleRepo consoleRepo, TShirtRepo tShirtRepo, InvoiceRepo invoiceRepo, SalesTaxRateRepo salesTaxRateRepo, ProcessingFeeRepo processingFeeRepo) {
        this.gameRepo = gameRepo;
        this.consoleRepo = consoleRepo;
        this.tShirtRepo = tShirtRepo;
        this.invoiceRepo = invoiceRepo;
        this.salesTaxRateRepo = salesTaxRateRepo;
        this.processingFeeRepo = processingFeeRepo;
    }

    private Pair<Double,Integer> checkInventory(String itemType, Integer itemId) { // return pair of price / quantity
        switch (itemType) {
            case "Game":
                Optional<Game> optionalGame = gameRepo.findById(itemId);
                if (optionalGame.isPresent()) {
                    Pair<Double,Integer> returnPair = new Pair<>(optionalGame.get().getPrice(),optionalGame.get().getQuantity()) ;
                    return returnPair;
                } else {
                    return null;
                }
            case "TShirt":
                Optional<TShirt> optionalTShirt = tShirtRepo.findById(itemId);
                if (optionalTShirt.isPresent()) {
                    Pair<Double,Integer> returnPair = new Pair<>(optionalTShirt.get().getPrice(),optionalTShirt.get().getQuantity()) ;
                    return returnPair;
                } else {
                    return null;
                }
            case "Console":
                Optional<Console> optionalConsole = consoleRepo.findById(itemId);
                if (optionalConsole.isPresent()) {
                    Pair<Double,Integer> returnPair = new Pair<>(optionalConsole.get().getPrice(),optionalConsole.get().getQuantity()) ;
                    return returnPair;
                } else {
                    return null;
                }
            default: // Is not a Game Console or TShirt
                return null;
        }
    }
    private void updateInventory(String itemType, Integer itemId, Integer invoiceQuantity){
        switch (itemType) {
            case "Game":
                Optional<Game> optionalGame = gameRepo.findById(itemId);
                if (optionalGame.isPresent()) {
                    Integer currQuantity = optionalGame.get().getQuantity();
                    currQuantity-=invoiceQuantity;
                    optionalGame.get().setQuantity(currQuantity);
                    gameRepo.save(optionalGame.get());
                }
                break;
            case "TShirt":
                Optional<TShirt> optionalTShirt = tShirtRepo.findById(itemId);
                if (optionalTShirt.isPresent()) {
                    Integer currQuantity = optionalTShirt.get().getQuantity();
                    currQuantity-=invoiceQuantity;
                    optionalTShirt.get().setQuantity(currQuantity);
                    tShirtRepo.save(optionalTShirt.get());
                }
                break;
            case "Console":
                Optional<Console> optionalConsole = consoleRepo.findById(itemId);
                if (optionalConsole.isPresent()) {
                    Integer currQuantity = optionalConsole.get().getQuantity();
                    currQuantity-=invoiceQuantity;
                    optionalConsole.get().setQuantity(currQuantity);
                    consoleRepo.save(optionalConsole.get());
                }
                break;
            default: // Is not a Game Console or TShirt
                break;
        }
    }
    @Transactional
    private Invoice buildInvoice(Invoice invoice) throws RuntimeException {
        // declare needed additions to final invoice
        Double total;
        Double processingFee;
        Double subtotal;
        Double salesTax;
        Double unitPrice;
        Optional<SalesTaxRate> optionalSalesTaxRate = salesTaxRateRepo.findByState(invoice.getState());
        Optional<ProcessingFee> optionalProcessingFee= processingFeeRepo.findByProductType(invoice.getItemType()); // do not need to check if present, that is done in checkInventory method
        if (invoice.getQuantity() > 0 && optionalSalesTaxRate.isPresent()) {
            Pair<Double,Integer> checkI = checkInventory(invoice.getItemType(), invoice.getQuantity());
            // check inventory
            if (checkI != null && checkI.getValue() >= invoice.getQuantity()) {
                // Do calculations
                updateInventory(invoice.getItemType(), invoice.getItemId(),invoice.getQuantity());
                unitPrice = checkI.getKey();
                 subtotal = invoice.getQuantity() * unitPrice;
                 salesTax = subtotal*optionalSalesTaxRate.get().getRate();
                if(invoice.getQuantity()>10){
                    processingFee = optionalProcessingFee.get().getFee() + 15.49;
                    total = subtotal + salesTax + processingFee;
                }
                else {
                    processingFee = optionalProcessingFee.get().getFee();
                    total = subtotal + salesTax + optionalProcessingFee.get().getFee();
                }
                invoice.setUnit_price(unitPrice);
                invoice.setSubtotal(subtotal);
                invoice.setTax(salesTax);
                invoice.setProcessingFee(processingFee);
                invoice.setTotal(total);
                return invoiceRepo.save(invoice);
            } else {
                throw new RuntimeException();
            }
        }
        else {
            throw new RuntimeException();
        }
    }
}
