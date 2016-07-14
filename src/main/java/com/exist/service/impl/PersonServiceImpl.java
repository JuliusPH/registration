package com.exist.service.impl;

import com.exist.dao.PersonRepository;
import com.exist.dto.PersonDto;
import com.exist.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonServiceImpl{
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ModelMapper mapper;

    public PersonServiceImpl(){

    }

    private Session getSession(){
        return sessionFactory.openSession();
    }

    public PersonDto findOne(Long id){
        return mapper.map(personRepository.findOne(id), PersonDto.class);
    }

    public List<PersonDto> findAll(){
        return personRepository.findAll().stream()
            .map(person -> mapper.map(person, PersonDto.class))
            .collect(Collectors.toList());
    }

    public PersonDto save(Person person){
        return mapper.map(personRepository.save(person), PersonDto.class);
    }

    public void delete(Person person){
        personRepository.delete(person);
    }
}
