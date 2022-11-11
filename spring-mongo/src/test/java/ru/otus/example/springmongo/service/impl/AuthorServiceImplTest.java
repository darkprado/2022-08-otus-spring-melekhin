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
import ru.otus.example.springmongo.service.AuthorService;
import ru.otus.example.springmongo.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author s.melekhin
 * @since 02 нояб. 2022 г.
 */
@SpringBootTest
public class AuthorServiceImplTest {

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
    private AuthorService authorService;

    private Author expected;

    @BeforeEach
    public void setUp() {
        expected = new Author("1", "A1", "A1");
    }

    @Test
    void save() {
        when(authorRepository.save(any())).thenReturn(expected);
        when(authorRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        String actual = authorService.save("A1", "A1");
        assertEquals("1", actual);
    }

    @Test
    void getAll() {
        when(authorRepository.findAll()).thenReturn(Collections.singletonList(expected));
        List<Author> allAuthor = authorService.findAll();
        assertEquals(1, allAuthor.size());
        assertEquals(expected, allAuthor.get(0));
    }

    @Test
    void getById() {
        when(authorRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        Author actual = authorService.findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        expected.setId("1");
        when(authorRepository.save(any())).thenReturn(expected);
        when(authorRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        String actual = authorService.update("1", "A1", "A1");
        assertEquals("1", actual);
    }

}
