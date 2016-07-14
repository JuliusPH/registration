package com.exist.service;

import com.exist.dto.PersonDto;
import com.exist.model.Person;

import java.util.List;

public interface PersonService{
    PersonDto findOne(Long id);

    List<PersonDto> findAll();

    PersonDto save(Person person);

    void delete(Person person);
}
