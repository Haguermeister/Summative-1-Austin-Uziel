package com.austinuziel.project1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "t_shirt")
public class TShirt extends SaleItem {
    @Id
    @Column(name = "t_shirt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer TShirtId;
    @NotNull(message = "You must supply a String for size")
    private String size;
    @NotNull(message = "You must supply a String for color")
    private String color;
    @NotNull(message = "You must supply a String for desciption")
    private String description;

    public TShirt() {
    }

    public TShirt(Double price, Integer quantity, Integer TShirtId, String size, String color, String description) {
        super(price, quantity);
        this.TShirtId = TShirtId;
        this.size = size;
        this.color = color;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return Objects.equals(TShirtId, tShirt.TShirtId) && Objects.equals(size, tShirt.size) && Objects.equals(color, tShirt.color) && Objects.equals(description, tShirt.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), TShirtId, size, color, description);
    }

    @Override
    public String toString() {
        return "TShirt{" +
                "TShirtId=" + TShirtId +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
