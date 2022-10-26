package ru.otus.example.springdata.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.example.springdata.domain.Book;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    @EntityGraph(value = "book-graph")
    List<Book> findAll();

    @EntityGraph(value = "book-graph")
    Optional<Book> findById(long id);

}
