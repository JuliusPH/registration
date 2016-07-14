package com.exist.dto;

import com.exist.model.Name;
import java.io.Serializable;

public class PersonDto implements Serializable{
    private Long id;
    private Name name;
    private AddressDto addressDto;

    public PersonDto(){
    }

    public PersonDto(Name name, AddressDto addressDto){
        this.name = name;
        this.addressDto = addressDto;
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

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
