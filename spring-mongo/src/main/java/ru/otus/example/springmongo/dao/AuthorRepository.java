package ru.otus.example.springmongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.example.springmongo.domain.Author;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface AuthorRepository extends MongoRepository<Author, String> {

}
