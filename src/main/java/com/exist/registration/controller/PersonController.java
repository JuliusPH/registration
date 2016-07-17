package com.exist.registration.controller;

import com.exist.registration.dto.PersonDto;
import com.exist.registration.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class PersonController{
    @Autowired
    private PersonService personService;

    @ModelAttribute("person")
    public PersonDto getPersonObect() {
        return new PersonDto();
    }

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView form(){
        ModelAndView modelAndView = new ModelAndView("form");
        PersonDto person = new PersonDto();
        modelAndView.addObject("person", person);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView form(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("form");
        PersonDto person = personService.findOne(id);
        modelAndView.addObject("person", person);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid PersonDto person, BindingResult result, RedirectAttributes redirectAttributes){
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        if(result.hasErrors()){
            modelAndView.addObject("person", person);
            return new ModelAndView("form");
        }
        else{
            if(personService.save(person) != null){
                redirectAttributes.addFlashAttribute("status", "positive");
                redirectAttributes.addFlashAttribute("message", person.getFullName() + " is successfully added.");
            }
            else{
                redirectAttributes.addFlashAttribute("status", "negative");
                redirectAttributes.addFlashAttribute("message", "An error occurs, please try again.");
            }
        }
        return new ModelAndView("redirect:/");
    }
}
