package com.exist.registration.service;

import com.exist.registration.dto.PersonDto;
import com.exist.registration.model.Person;

import java.util.List;

public interface PersonService{
    PersonDto findOne(Long id);

    List<PersonDto> findAll();

    PersonDto save(PersonDto personDto);

    void delete(PersonDto personDto);

    PersonDto mapToPersonDto(Person person);

    Person mapToPerson(PersonDto personDto);
}
