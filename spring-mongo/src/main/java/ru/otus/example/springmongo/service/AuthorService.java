package ru.otus.example.springmongo.service;

import java.util.List;

import ru.otus.example.springmongo.domain.Author;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
public interface AuthorService {

    List<Author> findAll();

    Author findById(String id);

    String save(String firstname, String lastname);

    void deleteById(String id);

    String update(String id, String firstname, String lastname);

}
