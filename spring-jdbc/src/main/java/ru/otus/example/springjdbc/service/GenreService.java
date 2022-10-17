package ru.otus.example.springjdbc.service;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
public interface GenreService {

    void findAll();

    void findById(long id);

    void save();

    void deleteById(long id);

    void update(long id);

}
