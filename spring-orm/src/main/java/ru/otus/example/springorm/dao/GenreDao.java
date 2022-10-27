package ru.otus.example.springorm.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.example.springorm.domain.Genre;

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
