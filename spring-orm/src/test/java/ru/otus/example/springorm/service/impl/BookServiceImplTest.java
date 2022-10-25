package ru.otus.example.springorm.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.example.springorm.dao.AuthorDao;
import ru.otus.example.springorm.dao.BookDao;
import ru.otus.example.springorm.dao.GenreDao;
import ru.otus.example.springorm.domain.Author;
import ru.otus.example.springorm.domain.Book;
import ru.otus.example.springorm.domain.Genre;
import ru.otus.example.springorm.service.BookService;
import ru.otus.example.springorm.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author s.melekhin
 * @since 19 окт. 2022 г.
 */
@SpringBootTest
public class BookServiceImplTest {

    @MockBean
    private IOService ioService;

    @MockBean
    private GenreDao genreDao;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private BookDao bookDao;

    @Autowired
    private BookService bookService;

    private Book expected;

    @BeforeEach
    public void setUp() {
        expected = new Book(0, "name", new Author(1, "firstname", "lastname"), new Genre(1, "genre"));
    }

    @Test
    void save() {
        when(bookDao.insert(expected)).thenReturn(1L);
        when(bookDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = bookService.save("name", new Author(1, "firstname", "lastname"), new Genre(1, "genre"));
        assertEquals(1L, actual);
    }

    @Test
    void getAll() {
        when(bookDao.getAll()).thenReturn(Collections.singletonList(expected));
        List<Book> allBooks = bookService.findAll();
        assertEquals(1, allBooks.size());
        assertEquals(expected, allBooks.get(0));
    }

    @Test
    void getById() {
        when(bookDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        Book actual = bookService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        expected.setId(1L);
        when(bookDao.update(expected)).thenReturn(1L);
        when(bookDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = bookService.update(1L, "name", new Author(1, "firstname", "lastname"), new Genre(1, "genre"));
        assertEquals(1L, actual);
    }

    @Test
    void deleteById() {
        when(bookDao.deleteById(1L)).thenReturn(1L);
        long actual = bookService.deleteById(1L);
        assertEquals(1, actual);
    }

}
