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
import ru.otus.example.springorm.service.AuthorService;
import ru.otus.example.springorm.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author s.melekhin
 * @since 19 окт. 2022 г.
 */
@SpringBootTest
public class AuthorServiceImplTest {

    @MockBean
    private IOService ioService;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private AuthorService authorService;

    private Author expected;

    @BeforeEach
    public void setUp() {
        expected = new Author(0, "firstname", "lastname");
    }

    @Test
    void save() {
        when(authorDao.insert(expected)).thenReturn(1L);
        when(authorDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = authorService.save("firstname", "lastname");
        assertEquals(1L, actual);
    }

    @Test
    void getAll() {
        when(authorDao.getAll()).thenReturn(Collections.singletonList(expected));
        List<Author> allAuthor = authorService.findAll();
        assertEquals(1, allAuthor.size());
        assertEquals(expected, allAuthor.get(0));
    }

    @Test
    void getById() {
        when(authorDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        Author actual = authorService.findById(1L);
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        expected.setId(1L);
        when(authorDao.update(expected)).thenReturn(1L);
        when(authorDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = authorService.update(1L, "firstname", "lastname");
        assertEquals(1L, actual);
    }

    @Test
    void deleteById() {
        when(authorDao.deleteById(1L)).thenReturn(1L);
        long actual = authorService.deleteById(1L);
        assertEquals(1, actual);
    }

}
