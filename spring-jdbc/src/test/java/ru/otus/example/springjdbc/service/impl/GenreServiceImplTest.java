package ru.otus.example.springjdbc.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import ru.otus.example.springjdbc.dao.AuthorDao;
import ru.otus.example.springjdbc.dao.BookDao;
import ru.otus.example.springjdbc.dao.GenreDao;
import ru.otus.example.springjdbc.domain.Genre;
import ru.otus.example.springjdbc.service.GenreService;
import ru.otus.example.springjdbc.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author s.melekhin
 * @since 19 окт. 2022 г.
 */
@SpringBootTest
public class GenreServiceImplTest {

    @MockBean
    private IOService ioService;

    @MockBean
    private GenreDao genreDAO;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private BookDao bookDao;

    @Autowired
    private GenreService genreService;

    private Genre expected;

    @BeforeEach
    public void setUp() {
        expected = new Genre("genre");
    }

    @Test
    void saveGenre() {
        when(genreDAO.insert(expected)).thenReturn(1L);
        when(genreDAO.getById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = genreService.save("genre");
        assertEquals(1L, actual);
    }

    @Test
    void getAllGenres() {
        when(genreDAO.getAll()).thenReturn(Collections.singletonList(expected));
        List<Genre> allGenre = genreService.findAll();
        assertEquals(1, allGenre.size());
        assertEquals(expected, allGenre.get(0));
    }

    @Test
    void getGenreById() {
        when(genreDAO.getById(1L)).thenReturn(Optional.ofNullable(expected));
        Genre actual = genreService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    void updateGenre() {
        expected.setId(1L);
        when(genreDAO.update(expected)).thenReturn(1L);
        when(genreDAO.getById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = genreService.update(1L, "genre");
        assertEquals(1L, actual);
    }

    @Test
    void deleteGenreById() {
        when(genreDAO.deleteById(1L)).thenReturn(1L);
        long actual = genreService.deleteById(1L);
        assertEquals(1, actual);
    }

}
