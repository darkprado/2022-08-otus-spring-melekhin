package ru.otus.example.springmongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.example.springmongo.domain.Genre;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface GenreRepository extends MongoRepository<Genre, String> {

}
