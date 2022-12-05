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
//import ru.otus.example.springmvcview.springmvcview.domain.Comment;
//import ru.otus.example.springmvcview.springmvcview.domain.Genre;
//import ru.otus.example.springmvcview.springmvcview.service.CommentService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
///**
// * @author s.melekhin
// * @since 02 нояб. 2022 г.
// */
//@SpringBootTest
//public class CommentServiceImplTest {
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
//    private CommentService commentService;
//
//    private Comment expected;
//
//    @BeforeEach
//    public void setUp() {
//        expected = new Comment(1L, "C1", new Book(1L, "B1", new Author(1, "A1", "A1"), new Genre(1, "G1")));
//    }
//
//    @Test
//    void save() {
//        when(commentRepository.save(any())).thenReturn(expected);
//        when(bookRepository.findById(1L)).thenReturn(Optional.ofNullable(new Book(1L , "B1", null, null)));
//        long actual = commentService.save("C1", 1L);
//        assertEquals(1L, actual);
//    }
//
//    @Test
//    void getAllByBook() {
//        when(commentRepository.findByBookId(1L)).thenReturn(Collections.singletonList(expected));
//        List<Comment> allComment = commentService.findAllByBook(1L);
//        assertEquals(1, allComment.size());
//        assertEquals(expected, allComment.get(0));
//    }
//
//    @Test
//    void getById() {
//        when(commentRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        Comment actual = commentService.findById(1L);
//        assertEquals(expected, actual);
//    }
//
//    @Test
//    void update() {
//        when(commentRepository.save(expected)).thenReturn(expected);
//        when(commentRepository.findById(1L)).thenReturn(Optional.ofNullable(expected));
//        long actual = commentService.update(1L, "C1");
//        assertEquals(1L, actual);
//    }
//
//}
