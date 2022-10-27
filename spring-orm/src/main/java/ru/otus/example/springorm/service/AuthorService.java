package ru.otus.example.springorm.service;

import java.util.List;

import ru.otus.example.springorm.domain.Author;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
public interface AuthorService {

    List<Author> findAll();

    Author findById(long id);

    long save(String firstname, String lastname);

    long deleteById(long id);

    long update(long id, String firstname, String lastname);

}
