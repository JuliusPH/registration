package com.exist.registration.dto;

import com.exist.registration.model.Name;

import javax.validation.Valid;
import java.io.Serializable;

public class PersonDto implements Serializable{
    private Long id;
    @Valid
    private Name name;
    @Valid
    private AddressDto address;

    public PersonDto(){
    }

    public PersonDto(Name name, AddressDto address){
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto addressDto) {
        this.address = addressDto;
    }

    public String getFullName(){
        return name != null ? name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName() : null;
    }
}
