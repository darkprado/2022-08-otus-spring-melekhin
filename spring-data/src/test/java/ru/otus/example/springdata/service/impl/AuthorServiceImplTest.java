package ru.otus.example.springdata.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.example.springdata.dao.AuthorRepository;
import ru.otus.example.springdata.dao.BookRepository;
import ru.otus.example.springdata.dao.CommentRepository;
import ru.otus.example.springdata.dao.GenreRepository;
import ru.otus.example.springdata.domain.Author;
import ru.otus.example.springdata.service.AuthorService;
import ru.otus.example.springdata.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        expected = new Author(0, "A1", "A1");
    }

    @Test
    void save() {
        when(authorRepository.save(expected)).thenReturn(expected);
        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = authorService.save("A1", "A1");
        assertEquals(1L, ++actual);
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
        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
        Author actual = authorService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        expected.setId(1L);
        when(authorRepository.save(expected)).thenReturn(expected);
        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = authorService.update(1L, "A1", "A1");
        assertEquals(1L, actual);
    }

}
