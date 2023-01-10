package ru.otus.springwebspa.springwebspa.service;

import java.util.List;

import ru.otus.springwebspa.springwebspa.domain.Author;
import ru.otus.springwebspa.springwebspa.domain.Book;
import ru.otus.springwebspa.springwebspa.domain.Genre;

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
