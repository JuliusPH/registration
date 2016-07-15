package com.exist.registration.service;

import com.exist.registration.dto.PersonDto;
import com.exist.registration.model.Person;

import java.util.List;

public interface PersonService{
    PersonDto findOne(Long id);

    List<PersonDto> findAll();

    PersonDto save(Person person);

    void delete(Person person);
}
