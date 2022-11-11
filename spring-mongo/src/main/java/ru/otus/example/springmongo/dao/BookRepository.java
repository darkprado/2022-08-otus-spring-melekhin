package ru.otus.example.springmongo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.example.springmongo.domain.Book;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findAllByAuthorId(String authorId);

    List<Book> findAllByGenreId(String genreId);

}
