package com.austinuziel.project1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice {

    //    FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Integer invoiceId;
    @NotNull(message = "You must supply a String for name")
    private String name;
    @NotNull(message = "You must supply a String for Street")
    private String street;
    @NotNull(message = "You must supply a String for City")
    private String city;
    @NotNull(message = "You must supply a String for State")
    private String state;
    @NotNull(message = "You must supply an String for zipcode")
    private String zipcode;
    @Column(name = "item_type")
    @NotNull(message = "You must supply a String for itemType")
    private String itemType;
    @Column(name = "item_id")
    @NotNull(message = "You must supply an Integer for itemId")
    private Integer itemId;
    @NotNull(message = "You must supply an Integer for quantity")
    private Integer quantity;
    @Column(name = "unit_price")
    @NotNull(message = "You must supply a float for unit_price")
    private Double unitPrice;
    @NotNull(message = "You must supply a float for subtotal")
    private Double subtotal;
    @NotNull(message = "You must supply a float for tax")
    private BigDecimal tax;
    @Column(name = "processing_fee")
    @NotNull(message = "You must supply a float for processingFee")
    private Double processingFee;
    @NotNull(message = "You must supply a long for total")
    private Double total;

    //    CONSTRUCTORS

    public Invoice() {
    }

    public Invoice(String name, String street, String city, String state, String zipcode, String itemType, Integer itemId, Integer quantity, Double unitPrice, Double subtotal, BigDecimal tax, Double processingFee, Double total) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
        this.tax = tax;
        this.processingFee = processingFee;
        this.total = total;
    }

    public Invoice(Integer invoiceId, String name, String street, String city, String state, String zipcode, String itemType, Integer itemId, Integer quantity, Double unitPrice, Double subtotal, BigDecimal tax, Double processingFee, Double total) {
        this.invoiceId = invoiceId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
        this.tax = tax;
        this.processingFee = processingFee;
        this.total = total;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public Double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Double processingFee) {
        this.processingFee = processingFee;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(invoiceId, invoice.invoiceId) && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipcode, invoice.zipcode) && Objects.equals(itemType, invoice.itemType) && Objects.equals(itemId, invoice.itemId) && Objects.equals(quantity, invoice.quantity) && Objects.equals(unitPrice, invoice.unitPrice) && Objects.equals(subtotal, invoice.subtotal) && Objects.equals(tax, invoice.tax) && Objects.equals(processingFee, invoice.processingFee) && Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipcode, itemType, itemId, quantity, unitPrice, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
