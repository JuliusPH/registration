package com.exist.registration.service.impl;

import com.exist.registration.dto.PersonDto;
import com.exist.registration.service.PersonService;
import com.exist.registration.repo.PersonRepository;
import com.exist.registration.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ModelMapper mapper;


    @Transactional(readOnly = true)
    public PersonDto findOne(Long id){
        return mapToPersonDto(personRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    public List<PersonDto> findAll(){
        List<Person> persons = personRepository.findAll();
        return persons.stream().map(person -> mapToPersonDto(person))
            .collect(Collectors.toList());
    }

    public PersonDto save(PersonDto personDto){
        Person person = mapToPerson(personDto);
        return mapToPersonDto(personRepository.save(person));
    }

    public void delete(PersonDto personDto){
        personRepository.delete(mapToPerson(personDto));
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public PersonDto mapToPersonDto(Person person){
        return mapper.map(person, PersonDto.class);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Person mapToPerson(PersonDto personDto){
        return mapper.map(personDto, Person.class);
    }
}
