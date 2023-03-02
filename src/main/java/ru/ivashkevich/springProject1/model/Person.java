package ru.ivashkevich.springProject1.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Person {

    private int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
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
