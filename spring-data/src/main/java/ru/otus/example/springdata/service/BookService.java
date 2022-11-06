package ru.otus.example.springdata.service;

import java.util.List;

import ru.otus.example.springdata.domain.Author;
import ru.otus.example.springdata.domain.Book;
import ru.otus.example.springdata.domain.Genre;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
public interface BookService {

    List<Book> findAll();

    Book findById(long id);

    long save(String name, Author author, Genre genre);

    void deleteById(long id);

    long update(long id, String name, Author author, Genre genre);

}
