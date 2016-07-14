package com.exist.dto;

import java.io.Serializable;

public class AddressDto implements Serializable{
    private Long id;
    private String streetNumber;
    private String barangay;
    private String city;

    public AddressDto(){}

    public AddressDto(String streetNumber, String barangay, String city) {
        this.streetNumber = streetNumber;
        this.barangay = barangay;
        this.city = city;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getBarangay() {
        return barangay;
    }

    public void setBarangay(String barangay) {
        this.barangay = barangay;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
