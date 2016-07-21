package com.exist.registration.service;

import com.exist.registration.dto.AddressDto;
import com.exist.registration.dto.PersonDto;
import com.exist.registration.model.Address;
import com.exist.registration.model.Name;
import com.exist.registration.model.Person;
import com.exist.registration.repo.PersonRepository;
import com.exist.registration.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class PersonServiceTest{
    @Mock
    private PersonRepository personRepository;
    @Spy
    private ModelMapper mapper = new ModelMapper();
    @InjectMocks
    private PersonService personService = new PersonServiceImpl();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPerson() throws Exception {
        when(personRepository.findOne(1L)).thenReturn(new Person());
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
        personService.findAll();
        verify(personRepository).findAll();
        verify(mapper).map(any(Person.class), eq(PersonDto.class));
        assertTrue(personService.findAll().size() > 0);
    }

    @Test
    public void shouldSavePerson() throws Exception {
        when(personRepository.save(any(Person.class))).thenReturn(new Person());
        PersonDto personDto = personService.save(new PersonDto());
        verify(personRepository, times(1)).save(any(Person.class));
        verify(mapper).map(any(Person.class), eq(PersonDto.class));
        assertNotNull(personDto);
    }

    @Test
    public void shouldDeletePerson() throws Exception {
        PersonDto personDto = new PersonDto();
        personService.delete(personDto);
        verify(personRepository).delete(any(Person.class));
        verify(mapper).map(any(PersonDto.class), eq(Person.class));
    }

    @Test
    public void shouldMapPersonToPersonDtoCorrect() throws Exception {
        Name name = new Name("Julius", "Canceran", "Lapugot");
        Address address = new Address("5", "Maysan", "Valenzuela City");
        Person person = new Person(name, address);
        PersonDto personDto = personService.mapToPersonDto(person);
        verify(mapper).map(any(Person.class), eq(PersonDto.class));
        assertNotNull(personDto);
        assertPersonDetails(person, personDto);
    }

    @Test
    public void shouldMapPersonDtoToPersonCorrect() throws Exception {
        Name name = new Name("Julius", "Canceran", "Lapugot");
        AddressDto addressDto = new AddressDto("5", "Maysan", "Valenzuela City");
        PersonDto personDto = new PersonDto(name, addressDto);
        Person person = personService.mapToPerson(personDto);
        verify(mapper).map(any(PersonDto.class), eq(Person.class));
        assertNotNull(person);
        assertPersonDetails(person, personDto);
    }

    private void assertPersonDetails(Person person, PersonDto personDto){
        assertEquals(person.getName().getFirstName(), personDto.getName().getFirstName());
        assertEquals(person.getName().getMiddleName(), personDto.getName().getMiddleName());
        assertEquals(person.getName().getLastName(), personDto.getName().getLastName());
        assertEquals(person.getAddress().getStreetNumber(), personDto.getAddress().getStreetNumber());
        assertEquals(person.getAddress().getBarangay(), personDto.getAddress().getBarangay());
        assertEquals(person.getAddress().getCity(), personDto.getAddress().getCity());
        assertEquals(person.getName().getFirstName() + " " +
            person.getName().getMiddleName() + " " +
            person.getName().getLastName(),
            personDto.getFullName());
    }
}