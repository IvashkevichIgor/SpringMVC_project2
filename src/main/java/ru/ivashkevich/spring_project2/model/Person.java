package ru.ivashkevich.spring_project2.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int personId;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 5, max = 100, message = "Длина ФИО должна быть не менее 5 символов и не более 100")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
    private int birthYear;
    public Person(){}

    public Person(int personId, String name, int birthYear) {
        this.personId = personId;
        this.name = name;
        this.birthYear = birthYear;
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

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}
