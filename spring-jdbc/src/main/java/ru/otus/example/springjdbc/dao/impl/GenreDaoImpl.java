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
import ru.otus.example.springjdbc.dao.GenreDao;
import ru.otus.example.springjdbc.dao.mappers.GenreMapper;
import ru.otus.example.springjdbc.domain.Genre;

/**
 * @author s.melekhin
 * @since 11 окт. 2022 г.
 */
@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Genre> getAll() {
        return jdbc.getJdbcOperations()
                .query("select g.id, g.name from genres g", new GenreMapper());
    }

    @Override
    public Optional<Genre> getById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject(
                    "select id, name from genres where id=:id",
                    Map.of("id", id), new GenreMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public long insert(Genre genre) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", genre.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "insert into genres (name) values (:name)", parameterSource, keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long update(Genre genre) {
        return jdbc.update(
                "update genres g set g.name=:name where g.id=:id",
                Map.of("id", genre.getId(), "name", genre.getName()));
    }

    @Override
    public long deleteById(long id) {
        return jdbc.update(
                "delete from genres where id=:id", Map.of("id", id));
    }

}
