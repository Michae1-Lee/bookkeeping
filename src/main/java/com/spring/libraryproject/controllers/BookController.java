package com.spring.libraryproject.controllers;

import com.spring.libraryproject.dao.BookDAO;
import com.spring.libraryproject.dao.PersonDAO;
import com.spring.libraryproject.models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    @Autowired
    public BookController(PersonDAO personDAO, BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        if(bookDAO.show(id).getPerson_id().isPresent()){
            return "showBookPerson";
        }
        return "showBook";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "editBook";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id") int id) {
        bookDAO.edit(book,id);
        return "redirect:/books";
    }
    @GetMapping
    public String allBooks(Model model) {
        model.addAttribute("bookList", bookDAO.showAll());
        return "bookList";
    }
    @GetMapping("/add")
    public String addPerson(@ModelAttribute("book") Book book){
        return "addBook";
    }
    @PostMapping("/addBookPost")
    public String addBookPost(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "addBook";
        }
        bookDAO.add(book);
        return "redirect:/books";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PostMapping("/{id}/link")
    public String link(@PathVariable("id") int id, @ModelAttribute Book book){
        bookDAO.link(book, id);
        return "redirect:/books/{id}";
    }
    @PostMapping("/{id}/release")
    public String link(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/{id}";
    }
}
