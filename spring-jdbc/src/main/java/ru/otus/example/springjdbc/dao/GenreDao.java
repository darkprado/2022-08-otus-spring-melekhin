package ru.otus.example.springjdbc.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.example.springjdbc.domain.Genre;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface GenreDao {

    long insert(Genre genre);

    long update(Genre genre);

    Optional<Genre> getById(long id);

    List<Genre> getAll();

    long deleteById(long id);

}
