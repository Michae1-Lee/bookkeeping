package com.spring.libraryproject.models;

public class Book {
    private int id;
    private int person_id;
    private String book_title;
    private int year_creation;

    public Book(){

    }
    public Book(int person_id, String book_title, int year_creation) {
        this.person_id = person_id;
        this.book_title = book_title;
        this.year_creation = year_creation;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public int getYear_creation() {
        return year_creation;
    }

    public void setYear_creation(int year_creation) {
        this.year_creation = year_creation;
    }
}
