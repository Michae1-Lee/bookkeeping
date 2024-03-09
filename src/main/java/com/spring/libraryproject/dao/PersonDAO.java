package com.spring.libraryproject.dao;

import com.spring.libraryproject.models.Book;
import com.spring.libraryproject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void add(Person person){
        jdbcTemplate.update("INSERT INTO PERSON(full_name, year_of_birth) VALUES(?,?)",
                person.getFull_name(), person.getYear_of_birth());
    }
    public List<Person> showAll(){
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
    }
    public void edit(Person updatedPerson, int id){
        jdbcTemplate.update("UPDATE person set full_name=?, year_of_birth=? WHERE id=?",
                updatedPerson.getFull_name(), updatedPerson.getYear_of_birth(), id);
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM PERSON WHERE id=?", id);
    }
    public List<Book> smth(int person_id){
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id = ?",new Object[]{person_id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}
