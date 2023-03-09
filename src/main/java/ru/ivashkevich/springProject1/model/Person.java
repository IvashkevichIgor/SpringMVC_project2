package ru.ivashkevich.springProject1.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 5, max = 100, message = "Длина ФИО должна быть не менее 5 символов и не более 100")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    private int birthYear;
    public Person(){}

    public Person(int personId, String name, int birthYear) {
        this.id = personId;
        this.name = name;
        this.birthYear = birthYear;
    }

    public int getPersonId() {
        return id;
    }

    public void setPersonId(int personId) {
        this.id = personId;
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

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
