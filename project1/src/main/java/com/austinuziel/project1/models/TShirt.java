package com.austinuziel.project1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_shirt")
public class TShirt extends SaleItem{
    @Id
    @Column(name = "t_shirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TShirtId;

    private String size;
    private String color;

    public TShirt() {
    }

    public TShirt(double price, Integer quantity, String description, Integer TShirtId, String size, String color) {
        super(price, quantity, description);
        this.TShirtId = TShirtId;
        this.size = size;
        this.color = color;
    }

    public Integer getTShirtId() {
        return TShirtId;
    }

    public void setTShirtId(Integer TShirtId) {
        this.TShirtId = TShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TShirt tShirt = (TShirt) o;
        return Objects.equals(TShirtId, tShirt.TShirtId) && Objects.equals(size, tShirt.size) && Objects.equals(color, tShirt.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), TShirtId, size, color);
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "TShirtId=" + TShirtId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
