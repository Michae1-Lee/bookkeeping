package com.spring.libraryproject.controllers;

import com.spring.libraryproject.dao.PersonDAO;
import com.spring.libraryproject.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class LibraryController {
    private final PersonDAO personDAO;
    @Autowired
    public LibraryController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String allPeople(@ModelAttribute("persom") List<Person> person) {
        return "peopleList";
    }
    @GetMapping("/add")
    public String addNew(@ModelAttribute("person") Person person){
        return "add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "add";
        }
        personDAO.add(person);
        return "redirect:/people";
    }
}
