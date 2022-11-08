package ru.otus.example.springmongo.service;

import java.util.List;

import ru.otus.example.springmongo.domain.Genre;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
public interface GenreService {

    List<Genre> findAll();

    Genre findById(String id);

    String save(String name);

    void deleteById(String id);

    String update(String id, String name);

}
