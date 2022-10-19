package com.austinuziel.project1.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@MappedSuperclass
abstract class SaleItem {
    @NotNull(message = "You must supply a Double for price")
    private Double price;
    @NotNull(message = "You must supply an Integer for manufacturer")
    private Integer quantity;

    public SaleItem() {
    }

    public SaleItem(double price, Integer quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Double.compare(saleItem.price, price) == 0 && Objects.equals(quantity, saleItem.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, quantity);
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
