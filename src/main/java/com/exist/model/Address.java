package com.exist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable{
    @Id
    private Long id;
    @Column(name = "street_number")
    private String streetNumber;
    @Column(name = "barangay")
    private String barangay;
    @Column(name = "city")
    private String city;

    public Address(){}

    public Address(String streetNumber, String barangay, String city){
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
