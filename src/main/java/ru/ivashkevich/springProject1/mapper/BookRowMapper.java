package ru.ivashkevich.springProject1.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ivashkevich.springProject1.model.Book;
import ru.ivashkevich.springProject1.model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Person owner = new Person(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), null);
        return new Book(resultSet.getInt(4), owner, resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7));
    }
}
