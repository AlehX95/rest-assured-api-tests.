package com.alex.api.models;

public class AddressModel {

    private String city;
    private String zip;

    // Constructor
    public AddressModel(String city, String zip) {
        this.city = city;
        this.zip = zip;
    }

    // Getters
    public String getCity() { return city; }
    public String getZip() { return zip; }

    // Setters optional
    public void setCity(String city) { this.city = city; }
    public void setZip(String zip) { this.zip = zip; }
}