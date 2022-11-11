package ru.otus.example.springmongo.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import ru.otus.example.springmongo.domain.Book;

/**
 * @author s.melekhin
 * @since 02 нояб. 2022 г.
 */
@DataMongoTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAllByAuthorId() {
        List<Book> allByAuthorId = bookRepository.findAllByAuthorId("1");
        assert !allByAuthorId.isEmpty();
        Assertions.assertEquals(1, allByAuthorId.size());
        Assertions.assertEquals("Война и мир", allByAuthorId.get(0).getName());
    }

    @Test
    void findAllByGenreId() {
        List<Book> allByGenreId = bookRepository.findAllByGenreId("2");
        assert !allByGenreId.isEmpty();
        Assertions.assertEquals(1, allByGenreId.size());
        Assertions.assertEquals("Король лир", allByGenreId.get(0).getName());
    }

}
