package ru.otus.example.springorm.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.example.springorm.domain.Author;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface AuthorDao {

    long insert(Author author);

    long update(Author author);

    Optional<Author> getById(long id);

    List<Author> getAll();

    long deleteById(long id);


}
