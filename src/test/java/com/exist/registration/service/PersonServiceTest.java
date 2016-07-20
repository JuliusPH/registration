package com.exist.registration.service;

import com.exist.registration.dto.AddressDto;
import com.exist.registration.dto.PersonDto;
import com.exist.registration.model.Person;
import com.exist.registration.repo.PersonRepository;
import com.exist.registration.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class PersonServiceTest{
    @Mock
    private PersonRepository personRepository;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private PersonService personService = new PersonServiceImpl();

    private AddressDto address1 = new AddressDto("1", "Barangay 1", "City 1");
    private AddressDto address2 = new AddressDto("2", "Barangay 2", "City 2");

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPerson() throws Exception {
        when(personRepository.findOne(1L)).thenReturn(new Person());
        when(mapper.map(any(Person.class), eq(PersonDto.class))).thenReturn(new PersonDto());
        personService.findOne(1L);
        verify(personRepository).findOne(anyLong());
        verify(mapper).map(any(Person.class), eq(PersonDto.class));
        assertNotNull(personService.findOne(1L));
    }

    @Test
    public void shouldReturnListWithNoPerson() throws Exception {
        when(personRepository.findAll()).thenReturn(new ArrayList<Person>());
        personService.findAll();
        verify(personRepository).findAll();
        assertTrue(personService.findAll().size() == 0);
    }

    @Test
    public void shouldReturnListWithPerson() throws Exception {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person());
        when(personRepository.findAll()).thenReturn(persons);
        when(mapper.map(any(Person.class), eq(PersonDto.class))).thenReturn(new PersonDto());
        personService.findAll();
        verify(personRepository).findAll();
        verify(mapper).map(any(Person.class), eq(PersonDto.class));
        assertTrue(personService.findAll().size() > 0);
    }

    @Test
    public void shouldSavePerson() throws Exception {
        when(personRepository.save(any(Person.class))).thenReturn(new Person());
        when(mapper.map(any(Person.class), eq(PersonDto.class))).thenReturn(new PersonDto());
        PersonDto personDto = personService.save(new PersonDto());
        verify(personRepository, times(1)).save(any(Person.class));
        verify(mapper).map(any(Person.class), eq(PersonDto.class));
        assertNotNull(personDto);
    }

    @Test
    public void shouldDeletePerson() throws Exception {
        PersonDto personDto = new PersonDto();
        when(mapper.map(any(PersonDto.class), eq(Person.class))).thenReturn(new Person());
        personService.delete(personDto);
        verify(personRepository).delete(any(Person.class));
        verify(mapper).map(any(PersonDto.class), eq(Person.class));
    }
}