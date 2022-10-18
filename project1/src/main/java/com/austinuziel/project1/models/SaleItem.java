package com.austinuziel.project1.models;


import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
abstract class SaleItem {

    private double price;
    private Integer quantity;
    private String description;

    public SaleItem() {
    }

    public SaleItem(double price, Integer quantity, String description) {
        this.price = price;
        this.quantity = quantity;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleItem saleItem = (SaleItem) o;
        return Double.compare(saleItem.price, price) == 0 && Objects.equals(quantity, saleItem.quantity) && Objects.equals(description, saleItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, quantity, description);
    }

    @Override
    public String toString() {
        return "SaleItem{" +
                "price=" + price +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                '}';
    }
}
