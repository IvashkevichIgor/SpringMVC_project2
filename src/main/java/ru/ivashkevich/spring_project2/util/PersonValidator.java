package ru.ivashkevich.spring_project2.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.ivashkevich.spring_project2.models.Person;
import ru.ivashkevich.spring_project2.services.PeopleService;

@Component
public class PersonValidator implements Validator {

    private final PeopleService personService;

    @Autowired
    public PersonValidator(PeopleService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(Person.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personService.hasPersonWithName(person.getName())){
            errors.rejectValue("name", "", "Человек с таким ФИО уже существует");
        }

    }
}
