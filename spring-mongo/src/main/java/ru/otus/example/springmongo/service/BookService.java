package ru.otus.example.springmongo.service;

import java.util.List;

import ru.otus.example.springmongo.domain.Author;
import ru.otus.example.springmongo.domain.Book;
import ru.otus.example.springmongo.domain.Genre;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
public interface BookService {

    List<Book> findAll();

    Book findById(String id);

    String save(String name, Author author, Genre genre);

    void deleteById(String id);

    String update(String id, String name, Author author, Genre genre);

}
