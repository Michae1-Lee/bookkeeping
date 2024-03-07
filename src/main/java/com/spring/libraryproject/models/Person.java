package com.spring.libraryproject.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String full_name;
    private int birth_year;
    public Person() {
        // Пустой конструктор нужен для работы Thymeleaf
    }
    public Person(String full_name, int birth_year) {
        this.full_name = full_name;
        this.birth_year = birth_year;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }
}
