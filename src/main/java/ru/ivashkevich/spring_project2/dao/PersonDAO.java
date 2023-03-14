package ru.ivashkevich.spring_project2.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivashkevich.spring_project2.model.Book;
import ru.ivashkevich.spring_project2.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAllPeople(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(int id){
        Person person = jdbcTemplate.query("SELECT * FROM person WHERE person_id=?", new BeanPropertyRowMapper<>(Person.class), id)
                .stream().findAny().orElse(null);

        return person;
    }

    public Optional<Person> getPersonByName(String name){
        return jdbcTemplate.query("SELECT * FROM person WHERE name=?", new BeanPropertyRowMapper<>(Person.class), name)
                .stream().findAny();
    }

    public void create(Person person){
        jdbcTemplate.update("INSERT INTO person(name, birth_year) VALUES (?, ?)", person.getName(), person.getBirthYear());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET name=?, birth_year=? WHERE person_id=?",
                updatedPerson.getName(), updatedPerson.getBirthYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    public List<Book> getPersonBookList(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }
}
