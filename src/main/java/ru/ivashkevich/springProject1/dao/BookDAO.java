package ru.ivashkevich.springProject1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.ivashkevich.springProject1.model.Book;
import ru.ivashkevich.springProject1.model.Person;

import java.util.List;
import java.util.Optional;

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
        return jdbcTemplate.query("SELECT * FROM book WHERE book_id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream().findAny().orElse(null);
    }

    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT person_id, name, birth_year FROM person JOIN book USING(person_id) WHERE book_id=?",
                new BeanPropertyRowMapper<>(Person.class), id).stream().findAny();
    }

    public void create(Book book){
        jdbcTemplate.update("INSERT INTO book(title, author, publication_year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE book SET title=?, author=?, publication_year=? WHERE book_id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getPublicationYear(), id);
    }

    public void setPersonId(int bookId, Person person){
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE book_id=?", person.getPersonId(), bookId);
    }

    public void deletePersonId(int id){
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE book_id=?", id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }
}
