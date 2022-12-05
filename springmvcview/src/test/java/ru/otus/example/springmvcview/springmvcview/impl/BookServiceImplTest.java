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
//import ru.otus.example.springmvcview.springmvcview.domain.Book;
//import ru.otus.example.springmvcview.springmvcview.domain.Genre;
//import ru.otus.example.springmvcview.springmvcview.service.BookService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
///**
// * @author s.melekhin
// * @since 02 нояб. 2022 г.
// */
//@SpringBootTest
//public class BookServiceImplTest {
//
//    @MockBean
//    private AuthorRepository authorRepository;
//
//    @MockBean
//    private BookRepository bookRepository;
//
//    @MockBean
//    private GenreRepository genreRepository;
//
//    @MockBean
//    private CommentRepository commentRepository;
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
//    private BookService bookService;
//
//    private Book expected;
//
//    @BeforeEach
//    public void setUp() {
//        expected = new Book(0, "B1", new Author(1, "A1", "A1"), new Genre(1, "G1"));
//    }
//
//    @Test
//    void save() {
//        when(bookRepository.save(expected)).thenReturn(expected);
//        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        long actual = bookService.save("B1", new Author(1, "A1", "A1"), new Genre(1, "G1"));
//        assertEquals(1L, ++actual);
//    }
//
//    @Test
//    void getAll() {
//        when(bookRepository.findAll()).thenReturn(Collections.singletonList(expected));
//        List<Book> allBooks = bookService.findAll();
//        assertEquals(1, allBooks.size());
//        assertEquals(expected, allBooks.get(0));
//    }
//
//    @Test
//    void getById() {
//        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        Book actual = bookService.findById(1L);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void update() {
//        expected.setId(1L);
//        when(bookRepository.save(expected)).thenReturn(expected);
//        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        long actual = bookService.update(1L, "B1", new Author(1, "A1", "A1"), new Genre(1, "G1"));
//        assertEquals(1L, actual);
//    }
//
//}
