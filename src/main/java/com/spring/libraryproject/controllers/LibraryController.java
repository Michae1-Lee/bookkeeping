package com.spring.libraryproject.controllers;

import com.spring.libraryproject.dao.PersonDAO;
import com.spring.libraryproject.models.Book;
import com.spring.libraryproject.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class LibraryController {
    private final PersonDAO personDAO;
    @Autowired
    public LibraryController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "person/showPerson";
    }
    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "person/editPerson";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id) {
        personDAO.edit(person,id);
        return "redirect:/people";
    }
    @GetMapping
    public String allPeople(Model model) {
        model.addAttribute("personList", personDAO.showAll());
        return "person/peopleList";
    }
    @GetMapping("/add")
    public String addPerson(@ModelAttribute("person") Person person){
        return "person/addPerson";
    }
    @PostMapping("/addPersonPost")
    public String addPersonPost(@ModelAttribute("person") Person person){
        personDAO.add(person);
        return "redirect:/people";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
