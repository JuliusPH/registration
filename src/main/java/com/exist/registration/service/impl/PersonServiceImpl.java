package com.exist.registration.service.impl;

import com.exist.registration.dto.PersonDto;
import com.exist.registration.service.PersonService;
import com.exist.registration.repo.PersonRepository;
import com.exist.registration.model.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return mapper.map(personRepository.findOne(id), PersonDto.class);
    }

    @Transactional(readOnly = true)
    public List<PersonDto> findAll(){
        Type targetListType = new TypeToken<List<PersonDto>>(){}.getType();
        return mapper.map(personRepository.findAll(), targetListType);
    }

    public PersonDto save(PersonDto person){
        return mapper.map(personRepository.save(mapper.map(person, Person.class)), PersonDto.class);
    }

    public void delete(PersonDto person){
        personRepository.delete(mapper.map(person, Person.class));
    }
}
