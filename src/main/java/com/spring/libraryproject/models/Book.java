package com.spring.libraryproject.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Optional;

/**
 * @author Neil Alishev
 */
public class Book {
    private int id;
    private Optional<Integer> person_id;

    public Optional<Integer> getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Optional<Integer> person_id) {
        this.person_id = person_id;
    }

    private String title;

    private String author;

    private int year;

    public Book() {

    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
