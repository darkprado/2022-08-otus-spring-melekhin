package ru.otus.example.springdata.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.otus.example.springdata.domain.Author;
import ru.otus.example.springdata.domain.Book;
import ru.otus.example.springdata.domain.Genre;

/**
 * @author s.melekhin
 * @since 02 нояб. 2022 г.
 */
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findById() {
        Book bookById = bookRepository.findById(1L).orElse(null);
        assert bookById != null;
        Book expectedBook = new Book(1, "B1", new Author(1, "A1", "A1"), new Genre(1, "G1"));
        Assertions.assertEquals(expectedBook, bookById);
    }

    @Test
    void findAll() {
        List<Book> all = bookRepository.findAll();
        assert !all.isEmpty();
        Assertions.assertEquals(3, all.size());
    }

}
