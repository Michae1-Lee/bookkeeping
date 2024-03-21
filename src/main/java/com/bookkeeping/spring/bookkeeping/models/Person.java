package com.bookkeeping.spring.bookkeeping.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "full_name")
    private String full_name;
    @Column(name = "year_of_birth")
    private int year_of_birth;
    @OneToMany(mappedBy = "person")
    private List<Book> book;

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public Person() {

    }
    public Person(String full_name, int year_of_birth) {
        this.full_name = full_name;
        this.year_of_birth = year_of_birth;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

}
