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
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = Application.class)
public class PersonServiceTest{
    @Autowired
    private PersonService personService;

    @Test
    public void testFindOne() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {
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

    }
}