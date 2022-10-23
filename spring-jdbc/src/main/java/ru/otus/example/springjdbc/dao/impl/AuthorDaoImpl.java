package ru.otus.example.springjdbc.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.dao.AuthorDao;
import ru.otus.example.springjdbc.dao.mappers.AuthorMapper;
import ru.otus.example.springjdbc.domain.Author;

/**
 * @author s.melekhin
 * @since 11 окт. 2022 г.
 */
@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Author author) {
        MapSqlParameterSource parSource = new MapSqlParameterSource();
        parSource.addValue("firstname", author.getFirstname());
        parSource.addValue("lastname", author.getLastname());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "insert into authors (firstname, lastname) values (:firstname, :lastname)",
                parSource, keyHolder, new String[] { "id" });
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long update(Author author) {
        return jdbc.update(
                "update authors a set a.firstname=:firstname, a.lastname=:lastname where a.id=:id",
                Map.of("id", author.getId(), "firstname", author.getFirstname(), "lastname", author.getLastname())
        );
    }

    @Override
    public Optional<Author> getById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject(
                    "select id, firstname, lastname from authors where id=:id",
                    Map.of("id", id), new AuthorMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Author> getAll() {
        return jdbc.getJdbcOperations()
                .query("select id, firstname, lastname from authors", new AuthorMapper());
    }

    @Override
    public long deleteById(long id) {
        return jdbc.update(
                "delete from authors where id=:id", Map.of("id", id));
    }

}
