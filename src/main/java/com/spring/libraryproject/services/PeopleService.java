package com.spring.libraryproject.services;

import com.spring.libraryproject.models.Book;
import com.spring.libraryproject.models.Person;
import com.spring.libraryproject.repositories.PeopleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public List<Person> showAll(){
        return peopleRepository.findAll();
    }
    public Person show(int id){
        return peopleRepository.findById(id).orElse(null);
    }
    public void add(Person person){
        peopleRepository.save(person);
    }
    public void edit(Person updatedPerson, int id){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
