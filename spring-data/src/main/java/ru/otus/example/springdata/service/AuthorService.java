package ru.otus.example.springdata.service;

import java.util.List;

import ru.otus.example.springdata.domain.Author;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
public interface AuthorService {

    List<Author> findAll();

    Author findById(long id);

    long save(String firstname, String lastname);

    void deleteById(long id);

    long update(long id, String firstname, String lastname);

}
