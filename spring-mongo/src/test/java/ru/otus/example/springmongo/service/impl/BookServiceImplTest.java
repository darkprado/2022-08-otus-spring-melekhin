package ru.otus.example.springmongo.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.example.springmongo.dao.AuthorRepository;
import ru.otus.example.springmongo.dao.BookRepository;
import ru.otus.example.springmongo.dao.CommentRepository;
import ru.otus.example.springmongo.dao.GenreRepository;
import ru.otus.example.springmongo.domain.Author;
import ru.otus.example.springmongo.domain.Book;
import ru.otus.example.springmongo.domain.Genre;
import ru.otus.example.springmongo.service.BookService;
import ru.otus.example.springmongo.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author s.melekhin
 * @since 02 нояб. 2022 г.
 */
@SpringBootTest
public class BookServiceImplTest {

    @MockBean
    private IOService ioService;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private BookService bookService;

    private Book expected;

    @BeforeEach
    public void setUp() {
        expected = new Book("1", "B1", new Author("1", "A1", "A1"), new Genre("1", "G1"));
    }

    @Test
    void save() {
        when(bookRepository.save(any())).thenReturn(expected);
        when(bookRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        String actual = bookService.save("B1", new Author("1", "A1", "A1"), new Genre("1", "G1"));
        assertEquals("1", actual);
    }

    @Test
    void getAll() {
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(expected));
        List<Book> allBooks = bookService.findAll();
        assertEquals(1, allBooks.size());
        assertEquals(expected, allBooks.get(0));
    }

    @Test
    void getById() {
        when(bookRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        Book actual = bookService.findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        expected.setId("1");
        when(bookRepository.save(any())).thenReturn(expected);
        when(bookRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        String actual = bookService.update("1", "B1", new Author("1", "A1", "A1"), new Genre("1", "G1"));
        assertEquals("1", actual);
    }

}
