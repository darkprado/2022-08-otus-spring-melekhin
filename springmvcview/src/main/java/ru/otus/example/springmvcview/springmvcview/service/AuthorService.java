package ru.otus.example.springmvcview.springmvcview.service;

import java.util.List;

import ru.otus.example.springmvcview.springmvcview.domain.Author;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
public interface AuthorService {

    List<Author> findAll();

    Author findById(long id);

    long save(String firstname, String lastname);

    void deleteById(long id);

    long update(long id, String firstname, String lastname);

}
