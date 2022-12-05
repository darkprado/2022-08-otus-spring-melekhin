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
//import ru.otus.example.springmvcview.springmvcview.domain.Author;
//import ru.otus.example.springmvcview.springmvcview.service.AuthorService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
///**
// * @author s.melekhin
// * @since 02 нояб. 2022 г.
// */
//@SpringBootTest
//public class AuthorServiceImplTest {
//
//    @MockBean
//    private AuthorRepository authorRepository;
//
////    @MockBean
////    private BookRepository bookRepository;
////
////    @MockBean
////    private GenreRepository genreRepository;
////
////    @MockBean
////    private CommentRepository commentRepository;
////
////    @MockBean
////    private AuthorController authorController;
////
////    @MockBean
////    private BookController bookController;
////
////    @MockBean
////    private GenreController genreController;
////
////    @MockBean
////    private CommentController commentController;
//
//    @Autowired
//    private AuthorService authorService;
//
//    private Author expected;
//
//    @BeforeEach
//    public void setUp() {
//        expected = new Author(0, "A1", "A1");
//    }
//
//    @Test
//    void save() {
//        when(authorRepository.save(expected)).thenReturn(expected);
//        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        long actual = authorService.save("A1", "A1");
//        assertEquals(1L, ++actual);
//    }
//
//    @Test
//    void getAll() {
//        when(authorRepository.findAll()).thenReturn(Collections.singletonList(expected));
//        List<Author> allAuthor = authorService.findAll();
//        assertEquals(1, allAuthor.size());
//        assertEquals(expected, allAuthor.get(0));
//    }
//
//    @Test
//    void getById() {
//        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        Author actual = authorService.findById(1L);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void update() {
//        expected.setId(1L);
//        when(authorRepository.save(expected)).thenReturn(expected);
//        when(authorRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        long actual = authorService.update(1L, "A1", "A1");
//        assertEquals(1L, actual);
//    }
//
//}
