package ru.otus.example.springjdbc.service;

import java.util.List;

import ru.otus.example.springjdbc.domain.Genre;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
public interface GenreService {

    List<Genre> findAll();

    Genre findById(long id);

    long save(String name);

    long deleteById(long id);

    long update(long id, String name);

}
