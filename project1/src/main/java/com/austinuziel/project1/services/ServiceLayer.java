package com.austinuziel.project1.services;

import com.austinuziel.project1.models.*;
import com.austinuziel.project1.repositories.*;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private Pair<Double,Integer> checkInventory(String itemType, Integer itemId) throws RuntimeException{ // return pair of price / quantity
        switch (itemType) {
            case "Game":
                Optional<Game> optionalGame = gameRepo.findById(itemId);
                if (optionalGame.isPresent()) {
                    Pair<Double,Integer> returnPair = new Pair<>(optionalGame.get().getPrice(),optionalGame.get().getQuantity()) ;
                    return returnPair;
                } else {
                    throw new RuntimeException("Item does not exist");
                }
            case "TShirt":
                Optional<TShirt> optionalTShirt = tShirtRepo.findById(itemId);
                if (optionalTShirt.isPresent()) {
                    Pair<Double,Integer> returnPair = new Pair<>(optionalTShirt.get().getPrice(),optionalTShirt.get().getQuantity()) ;
                    return returnPair;
                } else {
                    throw new RuntimeException("Item does not exist");
                }
            case "Console":
                Optional<Console> optionalConsole = consoleRepo.findById(itemId);
                if (optionalConsole.isPresent()) {
                    Pair<Double,Integer> returnPair = new Pair<>(optionalConsole.get().getPrice(),optionalConsole.get().getQuantity()) ;
                    return returnPair;
                } else {
                    throw new RuntimeException("Item does not exist");
                }
            default: // Is not a Game Console or TShirt
                throw new RuntimeException("Item is not a Game, Console or TShirt");
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
    public  Invoice saveFinishedInvoice(@Valid Invoice invoice){
        return invoiceRepo.save(invoice);
    }
    @Transactional
    public Invoice buildInvoice(Invoice invoice) throws RuntimeException {
        // declare needed additions to final invoice
        Double total;
        Double processingFee;
        Double subtotal;
        BigDecimal salesTax;
        Double unitPrice;
        Optional<SalesTaxRate> optionalSalesTaxRate = salesTaxRateRepo.findByState(invoice.getState());
        Optional<ProcessingFee> optionalProcessingFee= processingFeeRepo.findByProductType(invoice.getItemType()); // do not need to check if present, that is done in checkInventory method
        if (invoice.getQuantity() > 0 ) {
            if( optionalSalesTaxRate.isPresent()){
                Pair<Double,Integer> checkI = checkInventory(invoice.getItemType(), invoice.getItemId());
                // check inventory
                if (checkI.getValue() >= invoice.getQuantity()) {
                    // Do calculations
                    updateInventory(invoice.getItemType(), invoice.getItemId(),invoice.getQuantity());
                    unitPrice = checkI.getKey();
                     subtotal = invoice.getQuantity() * unitPrice;
                     salesTax = new BigDecimal(subtotal*optionalSalesTaxRate.get().getRate());
                    if(invoice.getQuantity()>10){
                        processingFee = optionalProcessingFee.get().getFee() + 15.49;
                        total = subtotal + salesTax.doubleValue() + processingFee;
                    }
                    else {
                        processingFee = optionalProcessingFee.get().getFee();
                        total = subtotal + salesTax.doubleValue() + optionalProcessingFee.get().getFee();
                    }
                    invoice.setUnitPrice(unitPrice);
                    invoice.setSubtotal(subtotal);
                    invoice.setTax(salesTax.setScale(2, RoundingMode.HALF_UP));
                    invoice.setProcessingFee(processingFee);
                    invoice.setTotal(total);
                    return saveFinishedInvoice(invoice);
                } else {
                    throw new RuntimeException("Requested purchase quantity is not available. Current stock quantity is " + checkI.getValue());
                }
            }
            else {
                throw new RuntimeException("State Tax Rate does not exist");
            }
        }
        else {
            throw new RuntimeException("Invoice purchase quantity is not grater than 0");
        }
    }
}
