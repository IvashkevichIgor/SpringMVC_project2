package ru.ivashkevich.springProject1.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Person {

    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Год рождения не должен быть пустым")
    //TODO перевести сообщения ошибок на русский
    @Min(value = 1900, message = "Year of birth should be more than 1900")
    private int birthYear;

    private List<Book> books;

    public Person(){}

    public Person(int id, String name, int birthYear, List<Book> books) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
