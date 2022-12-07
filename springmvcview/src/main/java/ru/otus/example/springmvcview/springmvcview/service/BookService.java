package ru.otus.example.springmvcview.springmvcview.service;

import java.util.List;

import ru.otus.example.springmvcview.springmvcview.domain.Author;
import ru.otus.example.springmvcview.springmvcview.domain.Book;
import ru.otus.example.springmvcview.springmvcview.domain.Genre;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
public interface BookService {

    List<Book> findAll();

    Book findById(long id);

    long save(String name, Author author, Genre genre);

    void deleteById(long id);

    long update(long id, String name, Author author, Genre genre);

}
