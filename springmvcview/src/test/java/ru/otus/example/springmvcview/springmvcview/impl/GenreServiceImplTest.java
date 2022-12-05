//package ru.otus.example.springmvcview.springmvcview.impl;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import ru.otus.example.springmvcview.springmvcview.controller.AuthorController;
//import ru.otus.example.springmvcview.springmvcview.controller.BookController;
//import ru.otus.example.springmvcview.springmvcview.controller.CommentController;
//import ru.otus.example.springmvcview.springmvcview.controller.GenreController;
//import ru.otus.example.springmvcview.springmvcview.dao.AuthorRepository;
//import ru.otus.example.springmvcview.springmvcview.dao.BookRepository;
//import ru.otus.example.springmvcview.springmvcview.dao.CommentRepository;
//import ru.otus.example.springmvcview.springmvcview.dao.GenreRepository;
//import ru.otus.example.springmvcview.springmvcview.domain.Genre;
//import ru.otus.example.springmvcview.springmvcview.service.GenreService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
///**
// * @author s.melekhin
// * @since 02 нояб. 2022 г.
// */
//@SpringBootTest
//public class GenreServiceImplTest {
//
//    @MockBean
//    private AuthorRepository authorRepository;
//
//    @MockBean
//    private BookRepository bookRepository;
//
//    @MockBean
//    private CommentRepository commentRepository;
//
//    @MockBean
//    private GenreRepository genreRepository;
//
//    @MockBean
//    private AuthorController authorController;
//
//    @MockBean
//    private BookController bookController;
//
//    @MockBean
//    private GenreController genreController;
//
//    @MockBean
//    private CommentController commentController;
//
//    @Autowired
//    private GenreService genreService;
//
//    private Genre expected;
//
//    @BeforeEach
//    public void setUp() {
//        expected = new Genre(0, "G1");
//    }
//
//    @Test
//    void save() {
//        when(genreRepository.save(expected)).thenReturn(expected);
//        when(genreRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        long actual = genreService.save("G1");
//        assertEquals(1L, ++actual);
//    }
//
//    @Test
//    void getAll() {
//        when(genreRepository.findAll()).thenReturn(Collections.singletonList(expected));
//        List<Genre> allGenre = genreService.findAll();
//        assertEquals(1, allGenre.size());
//        assertEquals(expected, allGenre.get(0));
//    }
//
//    @Test
//    void getById() {
//        when(genreRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        Genre actual = genreService.findById(1L);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void update() {
//        expected.setId(1L);
//        when(genreRepository.save(expected)).thenReturn(expected);
//        when(genreRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        long actual = genreService.update(1L, "G1");
//        assertEquals(1L, actual);
//    }
//}
