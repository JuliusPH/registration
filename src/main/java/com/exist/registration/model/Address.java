package com.exist.registration.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
