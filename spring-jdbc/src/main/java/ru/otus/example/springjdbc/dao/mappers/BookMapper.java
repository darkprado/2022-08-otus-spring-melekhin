package ru.otus.example.springjdbc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.otus.example.springjdbc.domain.Author;
import ru.otus.example.springjdbc.domain.Book;
import ru.otus.example.springjdbc.domain.Genre;

/**
 * @author s.melekhin
 * @since 17 окт. 2022 г.
 */
public class BookMapper  implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getLong("b_id"), rs.getString("b_name"),
                new Author(rs.getLong("a_id"), rs.getString("firstname"), rs.getString("lastname")),
                new Genre(rs.getLong("g_id"), rs.getString("g_name"))
        );
    }
}
