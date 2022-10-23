package ru.otus.example.springjdbc.dao.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.otus.example.springjdbc.domain.Author;

/**
 * @author s.melekhin
 * @since 17 окт. 2022 г.
 */
public class AuthorMapper implements RowMapper<Author> {

    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        return new Author(id, firstname, lastname);
    }

}
