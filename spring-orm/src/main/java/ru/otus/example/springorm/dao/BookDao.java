package ru.otus.example.springorm.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.example.springorm.domain.Book;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface BookDao {

    long insert(Book book);

    long update(Book book);

    Optional<Book> getById(long id);

    List<Book> getAll();

    long deleteById(long id);

}
