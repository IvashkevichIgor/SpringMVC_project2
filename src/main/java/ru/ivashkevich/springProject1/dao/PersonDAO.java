package ru.ivashkevich.springProject1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivashkevich.springProject1.model.Book;
import ru.ivashkevich.springProject1.model.Person;

import java.util.List;

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
        if (person != null)
            person.setBooks(getPersonBookList(id));

        return person;
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(name, birth_year) VALUES (?, ?)", person.getName(), person.getBirthYear());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET name=?, birth_year=? WHERE person_id=?",
                updatedPerson.getName(), updatedPerson.getBirthYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE person_id=?", id);
    }

    private List<Book> getPersonBookList(int id){
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?", new BeanPropertyRowMapper<>(Book.class), id);
    }
}
