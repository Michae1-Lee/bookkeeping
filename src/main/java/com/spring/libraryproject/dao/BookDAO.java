package com.spring.libraryproject.dao;

import com.spring.libraryproject.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Book book){
        jdbcTemplate.update("INSERT INTO book(author,title, year) VALUES(?,?,?)",book.getAuthor(),
                book.getTitle(), book.getYear());
    }
    public List<Book> showAll(){
        return jdbcTemplate.query("SELECT * FROM BOOK", new BeanPropertyRowMapper<>(Book.class));
    }
    public void edit(Book updatedBook, int id){
        jdbcTemplate.update("UPDATE book set author=?,title=?, year=? WHERE id=?",updatedBook.getAuthor(),
                updatedBook.getTitle(), updatedBook.getYear(), id);
    }
    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM PERSON WHERE id=?", id);
    }
    public void link(Book book, int id){
        Integer actualValue = 9999;
        if (book.getPerson_id().isPresent()) {
            actualValue = book.getPerson_id().get();
        }
        jdbcTemplate.update("UPDATE book set person_id = ? where id = ?", actualValue, id);
    }
    public void release(int id){
        jdbcTemplate.update("UPDATE book set person_id=null where id = ?", id);
    }
}
