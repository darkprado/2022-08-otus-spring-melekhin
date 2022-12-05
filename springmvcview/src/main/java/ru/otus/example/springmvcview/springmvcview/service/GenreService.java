package ru.otus.example.springmvcview.springmvcview.service;

import java.util.List;

import ru.otus.example.springmvcview.springmvcview.domain.Genre;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
public interface GenreService {

    List<Genre> findAll();

    Genre findById(long id);

    long save(String name);

    void deleteById(long id);

    long update(long id, String name);

}
