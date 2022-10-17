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

import lombok.AllArgsConstructor;
import ru.otus.example.springjdbc.dao.BookDao;
import ru.otus.example.springjdbc.dao.mappers.BookMapper;
import ru.otus.example.springjdbc.domain.Book;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public long insert(Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("name", book.getName());
        parameterSource.addValue("author_id", book.getAuthor().getId());
        parameterSource.addValue("genre_id", book.getGenre().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                "insert into books (name, author_id, genre_id) values (:name, :author_id, :genre_id)",
                parameterSource, keyHolder, new String[]{"id"});
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public long update(Book book) {
        return jdbc.update(
                "update books set name=:name, author_id=:author_id, genre_id=:genre_id where id=:id",
                Map.of("id", book.getId(), "name", book.getName(), "author_id", book.getAuthor().getId(), "genre_id", book.getGenre().getId()));
    }

    @Override
    public Optional<Book> getById(long id) {
        String sql = "select b.id as b_id, b.name as b_name, a.id as a_id, a.firstname, a.lastname, g.id as g_id, g.name as g_name from books b " +
                "left join authors a on b.author_id=a.id " +
                "left join genres g on b.genre_id=g.id where b.id=:id";
        try {
            return Optional.ofNullable(jdbc.queryForObject(sql, Map.of("id", id), new BookMapper()));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> getAll() {
        String sql = "select b.id as b_id, b.name as b_name, a.id as a_id, a.firstname, a.lastname, g.id as g_id, g.name as g_name from books b " +
                "left join authors a on b.author_id=a.id " +
                "left join genres g on b.genre_id=g.id ";
        return jdbc.query(sql, new BookMapper());
    }

    @Override
    public long deleteById(long id) {
        return jdbc.update(
                "delete from books where id=:id", Map.of("id", id));
    }

}
