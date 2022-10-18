package com.austinuziel.project1.viewModel;


import java.util.Objects;

public class InvoiceModelView {

    //    FIELDS
    private String Name;

    private String street;

    private String city;

    private String state;

    private int zipCode;

    private String itemType;

    private int ItemId;

    private int quantity;

    //    CONSTRUCTORS
    public InvoiceModelView() {
    }

    public InvoiceModelView(String name, String street, String city, String state, int zipCode, String itemType, int itemId, int quantity) {
        Name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.itemType = itemType;
        ItemId = itemId;
        this.quantity = quantity;
    }

    //    GETTERS AND SETTERS
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceModelView that = (InvoiceModelView) o;
        return zipCode == that.zipCode && ItemId == that.ItemId && quantity == that.quantity && Objects.equals(Name, that.Name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(itemType, that.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, street, city, state, zipCode, itemType, ItemId, quantity);
    }

    @Override
    public String toString() {
        return "InvoiceModelView{" +
                "Name='" + Name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", itemType='" + itemType + '\'' +
                ", ItemId=" + ItemId +
                ", quantity=" + quantity +
                '}';
    }
}
