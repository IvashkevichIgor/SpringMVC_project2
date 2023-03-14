package ru.ivashkevich.spring_project2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivashkevich.spring_project2.models.Person;
import ru.ivashkevich.spring_project2.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> getAllPeople(){
        return peopleRepository.findAll();
    }

    public Person getPersonById(int id){
        return peopleRepository.findById(id).orElse(null);
    }

    public Person getPersonByName(String name){
        return peopleRepository.findByName(name);
    }

    @Transactional
    public void create(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
