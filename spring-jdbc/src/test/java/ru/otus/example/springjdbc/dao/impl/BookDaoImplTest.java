package ru.otus.example.springjdbc.dao.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import ru.otus.example.springjdbc.domain.Author;
import ru.otus.example.springjdbc.domain.Book;
import ru.otus.example.springjdbc.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author s.melekhin
 * @since 17 окт. 2022 г.
 */
@JdbcTest
@Import(BookDaoImpl.class)
public class BookDaoImplTest {

    @Autowired
    private BookDaoImpl bookDao;

    @Test
    void getAll() {
        List<Book> all = bookDao.getAll();
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void getById() {
        Book book = bookDao.getById(1L).orElse(null);
        assert book != null;
        assertThat(book.getName()).isEqualTo("B1");
    }

    @Test
    void deleteById() {
        long before = bookDao.getAll().size();
        bookDao.deleteById(3);
        long after = bookDao.getAll().size();
        assertThat(before).isGreaterThan(after);
    }

    @Test
    void insert() {
        Book book = new Book("test", new Author(1, "A1", "A1"), new Genre(1, "G1"));
        long before = bookDao.getAll().size();
        long id = bookDao.insert(book);
        long after = bookDao.getAll().size();
        assertThat(before).isLessThan(after);
        assertThat(id).isEqualTo(4);
        Book bookById = bookDao.getById(id).orElse(null);
        assert bookById != null;
        assertSame(book.getName(), bookById.getName());
    }

    @Test
    void update() {
        Book book = new Book(1, "test", new Author(1, "A1", "A1"), new Genre(1, "G1"));
        bookDao.update(book);
        Book bookById = bookDao.getById(1).orElse(null);
        assert bookById != null;
        assertThat(bookById).isEqualTo(book);
    }

}
