package com.exist.registration.controller;

import com.exist.registration.dto.AddressDto;
import com.exist.registration.dto.PersonDto;
import com.exist.registration.model.Name;
import com.exist.registration.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PersonControllerTest {
    @Mock
    private PersonService personService;
    @InjectMocks
    private PersonController personController;
    private MockMvc mockMvc;
    private PersonDto personDto;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        Name name = new Name("Julius", "Canceran", "Lapugot");
        AddressDto addressDto = new AddressDto("5", "Maysan", "Valenzuela City");
        personDto = new PersonDto();
        personDto.setId(1L);
        personDto.setName(name);
        personDto.setAddress(addressDto);
    }

    @Test
    public void shouldListPersonDtosInIndex() throws Exception{
        List<PersonDto> personDtos = new ArrayList<>();
        personDtos.add(new PersonDto());
        when(personService.findAll()).thenReturn(personDtos);
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("persons", personDtos))
                .andExpect(view().name("index"));
        verify(personService).findAll();
        assertNotNull(personService.findAll());
    }

    @Test
    public void shouldViewForm() throws Exception{
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("form"));
    }

    @Test
    public void shouldReturnPersonDto() throws Exception{
        when(personService.findOne(1L)).thenReturn(personDto);
        mockMvc.perform(get("/update/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(model().attribute("person", personDto))
                .andExpect(view().name("form"));
        verify(personService).findOne(anyLong());
    }

    @Test
    public void shouldSaveOrUpdatePersonDto() throws Exception{
        when(personService.save(any(PersonDto.class))).thenReturn(personDto);
        mockMvc.perform(post("/save")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("name.firstName", "Julius")
                    .param("name.middleName", "Canceran")
                    .param("name.lastName", "Lapugot")
                    .param("address.streetNumber", "5")
                    .param("address.barangay", "Maysan")
                    .param("address.city", "Valenzuela City"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("status", "success"))
                .andExpect(flash().attribute("message", personDto.getFullName() + " is successfully added."));
        ArgumentCaptor<PersonDto> argumentCaptor = ArgumentCaptor.forClass(PersonDto.class);
        verify(personService, times(1)).save(argumentCaptor.capture());
        verifyNoMoreInteractions(personService);

        assertThat(argumentCaptor.getValue().getName().getFirstName(), equalTo(personDto.getName().getFirstName()));
        assertThat(argumentCaptor.getValue().getName().getMiddleName(), equalTo(personDto.getName().getMiddleName()));
        assertThat(argumentCaptor.getValue().getName().getLastName(), equalTo(personDto.getName().getLastName()));
        assertThat(argumentCaptor.getValue().getAddress().getStreetNumber(), equalTo(personDto.getAddress().getStreetNumber()));
        assertThat(argumentCaptor.getValue().getAddress().getBarangay(), equalTo(personDto.getAddress().getBarangay()));
        assertThat(argumentCaptor.getValue().getAddress().getCity(), equalTo(personDto.getAddress().getCity()));
        assertThat(argumentCaptor.getValue().getFullName(), equalTo(personDto.getFullName()));
    }

    @Test
    public void shouldDeletePersonDto() throws Exception{
        when(personService.findOne(1L)).thenReturn(personDto);
        mockMvc.perform(post("/delete/{id}", 1L))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"))
                .andExpect(flash().attribute("status", "success"))
                .andExpect(flash().attribute("message", personDto.getFullName() + " is successfully deleted."));
    }
}