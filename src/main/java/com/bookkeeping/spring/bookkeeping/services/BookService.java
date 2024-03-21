package com.bookkeeping.spring.bookkeeping.services;


import com.bookkeeping.spring.bookkeeping.models.Book;
import com.bookkeeping.spring.bookkeeping.models.Person;
import com.bookkeeping.spring.bookkeeping.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void add(Book book) {
        bookRepository.save(book);
    }

    public List<Book> showAll() {
        return bookRepository.findAll();
    }

    public List<Integer> findBook(Book book) {
        Integer id = null;
        List<Book> found = bookRepository.findByTitleStartingWithIgnoreCase(book.getBookName());
        List<Integer> ids = new ArrayList<>();
        for (Book x : found) {
            ids.add(x.getId());
        }
        return ids;
    }

    public List<Book> showPageAll(String page, String booksPerPage) {
        return bookRepository.findAll(PageRequest.of(Integer.parseInt(page), Integer.parseInt(booksPerPage))).getContent();
    }

    public void edit(Book updatedBook, int id) {
        updatedBook.setId(id);
        bookRepository.save(updatedBook);
    }

    public Book show(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    public void link(Person person, Integer bookId) {
        bookRepository.findById(bookId).get().setPerson(person);
    }

    public void release(Integer id) {
        bookRepository.findById(id).get().setPerson(null);
    }

    public List<Book> showPersonBooks(int id) {
        return bookRepository.findAllByperson_id(id);
    }
}
