package com.exist.registration.controller;

import com.exist.registration.dto.PersonDto;
import com.exist.registration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.PersistenceException;
import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonController{
    @Autowired
    private PersonService personService;

    @ModelAttribute("personDto")
    public PersonDto getPersonDtoObect() {
        return new PersonDto();
    }

    @GetMapping("/")
    public String index(Model model){
        List<PersonDto> personsDtos = personService.findAll();
        model.addAttribute("persons", personsDtos);
        return "index";
    }

    @GetMapping("/add")
    public String form(){
        return "form";
    }

    @GetMapping("/update/{id}")
    public String form(@PathVariable Long id, Model model) throws NullPointerException{
        PersonDto personDto = personService.findOne(id);
        model.addAttribute("person", personDto);
        return "form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("personDto") PersonDto personDto, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "form";
        }
        else{
            if(personService.save(personDto) != null){
                redirectAttributes.addFlashAttribute("status", "success");
                redirectAttributes.addFlashAttribute("message", personDto.getFullName() + " is successfully added.");
            }
            else{
                redirectAttributes.addFlashAttribute("status", "failed");
                redirectAttributes.addFlashAttribute("message", "An error occurs, please try again.");
            }
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        PersonDto personDto = personService.findOne(id);
        try{
            personService.delete(personDto);
            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", personDto.getFullName() + " is successfully deleted.");
        }
        catch(PersistenceException ex){
            redirectAttributes.addFlashAttribute("status", "failed");
            redirectAttributes.addFlashAttribute("message", "An error occurs, please try again.");
        }
        return "redirect:/";
    }
}
