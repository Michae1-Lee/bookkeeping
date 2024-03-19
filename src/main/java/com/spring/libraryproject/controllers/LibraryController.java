package com.spring.libraryproject.controllers;

import com.spring.libraryproject.models.Person;
import com.spring.libraryproject.services.BookService;
import com.spring.libraryproject.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;
import java.util.Collections;

@Controller
@RequestMapping("/people")
public class LibraryController {
    private final PeopleService peopleService;
    private final BookService bookService;
    @Autowired
    public LibraryController(PeopleService peopleService, BookService bookService) {
        this.peopleService = peopleService;
        this.bookService = bookService;
    }
    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.show(id));
        model.addAttribute("books", bookService.showPersonBooks(id));
        return "person/showPerson";
    }
    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", peopleService.show(id));
        return "person/editPerson";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, @PathVariable("id") int id) {
        peopleService.edit(person,id);
        return "redirect:/people";
    }
    @GetMapping
    public String allPeople(Model model) {
        model.addAttribute("personList", peopleService.showAll());
        return "person/peopleList";
    }
    @GetMapping("/add")
    public String addPerson(@ModelAttribute("person") Person person){
        return "person/addPerson";
    }
    @PostMapping("/addPersonPost")
    public String addPersonPost(@ModelAttribute("person") Person person){
        peopleService.add(person);
        return "redirect:/people";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }
}
