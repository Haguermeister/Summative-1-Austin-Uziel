package com.austinuziel.project1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "console")
public class GameConsole extends SaleItem{
    @Id
    @Column(name = "console_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consoleId;

    @Column(name = "memory_amount")
    private String memoryAmount;

    private String processor;
    @Column(unique=true)
    private String model;
    private String manufacturer;

    public GameConsole() {
    }

    public GameConsole(double price, Integer quantity, String description, Integer consoleId, String memoryAmount, String processor, String model, String manufacturer) {
        super(price, quantity, description);
        this.consoleId = consoleId;
        this.memoryAmount = memoryAmount;
        this.processor = processor;
        this.model = model;
        this.manufacturer = manufacturer;
    }

    public Integer getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(Integer consoleId) {
        this.consoleId = consoleId;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GameConsole that = (GameConsole) o;
        return Objects.equals(consoleId, that.consoleId) && Objects.equals(memoryAmount, that.memoryAmount) && Objects.equals(processor, that.processor) && Objects.equals(model, that.model) && Objects.equals(manufacturer, that.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), consoleId, memoryAmount, processor, model, manufacturer);
    }

    @Override
    public String toString() {
        return "GameConsole{" +
                "consoleId=" + consoleId +
                ", memoryAmount='" + memoryAmount + '\'' +
                ", processor='" + processor + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                '}';
    }
}
