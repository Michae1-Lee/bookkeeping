package com.spring.libraryproject.controllers;

import com.spring.libraryproject.models.Book;
import com.spring.libraryproject.services.BookService;
import com.spring.libraryproject.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;
    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }
    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.show(id));
        if(bookService.show(id).getPerson() != null){
            return "book/showBookPerson";
        }
        return "book/showBook";
    }
    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.show(id));
        return "book/editBook";
    }
    @PostMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, @PathVariable("id") int id) {
        bookService.edit(book,id);
        return "redirect:/books";
    }
    @GetMapping
    public String allBooks(Model model,
                           @RequestParam(value = "page",required = false) String page,
                           @RequestParam(value = "books_per_page",required = false) String booksPerPage) {
        if(page == null && booksPerPage==null){
            model.addAttribute("bookList", bookService.showAll());
        }
        else{
            model.addAttribute("bookList", bookService.showPageAll(page,booksPerPage));
        }
        return "book/bookList";
    }
    @GetMapping("/search")
    public String search(@ModelAttribute("book") Book book){
        return "book/searchBook";
    }
    @PostMapping("/search")
    public String searchBook(@ModelAttribute("book") Book book, Model model){
        List<Integer> ids = bookService.findBook(book);
        model.addAttribute("book", book);
        List<Book> books = new ArrayList<>();
        for(Integer id : ids){
            books.add(bookService.show(id));
        }
        model.addAttribute("books",books);
        if(ids.isEmpty()){
            return "book/bookNotFound";
        }
        return "book/foundBooks";
    }
    @GetMapping("/add")
    public String addPerson(@ModelAttribute("book") Book book){
        return "book/addBook";
    }
    @PostMapping("/addBookPost")
    public String addBookPost(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "book/addBook";
        }
        bookService.add(book);
        return "redirect:/books";
    }
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        bookService.delete(id);
        return "redirect:/books";
    }
    @PostMapping("/{id}/link")
    public String link(@PathVariable("id") int id, @ModelAttribute Book book){
        bookService.link(peopleService.show(book.getPersonId()), id);
        return "redirect:/books/{id}";
    }
    @PostMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookService.release(id);
        return "redirect:/books/{id}";
    }
}
