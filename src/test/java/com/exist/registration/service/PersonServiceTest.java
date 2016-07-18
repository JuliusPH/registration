package com.exist.registration.service;

import com.exist.registration.Application;
import com.exist.registration.dto.AddressDto;
import com.exist.registration.dto.PersonDto;
import com.exist.registration.model.Name;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
public class PersonServiceTest{
    @Autowired
    private PersonService personService;

    @Test
    public void testFindOne() throws Exception {
        Name name = new Name("Julius", "Canceran", "Lapugot");
        AddressDto address = new AddressDto("5", "Maysan", "Valenzuela City");
        personService.save(new PersonDto(name, address));

        assertNotNull(personService.findOne(1L));
    }

    @Test
    public void testFindAll() throws Exception {
        Name name1 = new Name("Julius", "Canceran", "Lapugot");
        AddressDto address1 = new AddressDto("5", "Maysan", "Valenzuela City");

        Name name2 = new Name("Michaela", "Aldaba", "Policarpio");
        AddressDto address2 = new AddressDto("614", "Coloong 1", "Valenzuela City");

        personService.save(new PersonDto(name1, address1));
        personService.save(new PersonDto(name2, address2));

        assertTrue(personService.findAll().size() == 2);
    }

    @Test
    public void testSave() throws Exception {
        int size = personService.findAll().size();

        Name name = new Name("Julius", "Canceran", "Lapugot");
        AddressDto address = new AddressDto("5", "Maysan", "Valenzuela City");
        personService.save(new PersonDto(name, address));

        assertTrue(size < personService.findAll().size());
    }

    @Test
    public void testDelete() throws Exception {
        Name name = new Name("Julius", "Canceran", "Lapugot");
        AddressDto address = new AddressDto("5", "Maysan", "Valenzuela City");
        PersonDto person = personService.save(new PersonDto(name, address));
        personService.delete(person);
    }
}