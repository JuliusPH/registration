package com.exist.registration.dto;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AddressDto implements Serializable{
    private Long id;
    @NotNull
    @Length(min = 1, max = 50)
    @Column(name = "first_name")
    private String streetNumber;
    @NotNull
    @Length(min = 1, max = 50)
    @Column(name = "first_name")
    private String barangay;
    @NotNull
    @Length(min = 1, max = 50)
    @Column(name = "first_name")
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
