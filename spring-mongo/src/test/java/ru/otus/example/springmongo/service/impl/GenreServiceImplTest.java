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
import ru.otus.example.springmongo.domain.Genre;
import ru.otus.example.springmongo.service.GenreService;
import ru.otus.example.springmongo.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author s.melekhin
 * @since 02 нояб. 2022 г.
 */
@SpringBootTest
public class GenreServiceImplTest {

    @MockBean
    private IOService ioService;

    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private GenreService genreService;

    private Genre expected;

    @BeforeEach
    public void setUp() {
        expected = new Genre("1", "G1");
    }

    @Test
    void save() {
        when(genreRepository.save(any())).thenReturn(expected);
        when(genreRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        String actual = genreService.save("G1");
        assertEquals("1", actual);
    }

    @Test
    void getAll() {
        when(genreRepository.findAll()).thenReturn(Collections.singletonList(expected));
        List<Genre> allGenre = genreService.findAll();
        assertEquals(1, allGenre.size());
        assertEquals(expected, allGenre.get(0));
    }

    @Test
    void getById() {
        when(genreRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        Genre actual = genreService.findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        expected.setId("1");
        when(genreRepository.save(any())).thenReturn(expected);
        when(genreRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        String actual = genreService.update("1", "G1");
        assertEquals("1", actual);
    }
}
