package ru.ivashkevich.springProject1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivashkevich.springProject1.mapper.BookRowMapper;
import ru.ivashkevich.springProject1.model.Book;
import ru.ivashkevich.springProject1.model.Person;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAllBooks(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book getBookById(int id){
        return jdbcTemplate.query("SELECT * FROM person JOIN book USING (person_id) WHERE book_id=?", new BookRowMapper(), id)
                .stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book(title, author, publication_year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    /*public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET name=?, birth_year=? WHERE person_id=?",
                updatedPerson.getName(), updatedPerson.getBirthYear(), id);
    }*/

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }
}
