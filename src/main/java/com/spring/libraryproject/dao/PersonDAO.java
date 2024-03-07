package com.spring.libraryproject.dao;

import com.spring.libraryproject.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void show(List<Person> personList){

    }
    public void index(){

    }
    public void update(){

    }
}
