package ru.ivashkevich.springProject1.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class Person {

    private int personId;
    @NotEmpty(message = "Имя не должно быть пустым")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    private int birthYear;

    private List<Book> books;

    public Person(){}

    public Person(int personId, String name, int birthYear, List<Book> books) {
        this.personId = personId;
        this.name = name;
        this.birthYear = birthYear;
        this.books = books;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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
