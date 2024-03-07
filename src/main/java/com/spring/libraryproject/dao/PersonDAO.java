package com.spring.libraryproject.dao;

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
        jdbcTemplate.update("INSERT INTO PERSON(FULL_NAME, BIRTH_YEAR) VALUES(?,?)",
                person.getFull_name(), person.getBirth_year());
    }
    public List<Person> showAll(){
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
    }
    public void edit(Person updatedPerson, int id){
        jdbcTemplate.update("UPDATE person set full_name=?, birth_year=? WHERE id=?",
                updatedPerson.getFull_name(), updatedPerson.getBirth_year(), id);
    }
    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE id = ?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }
}
