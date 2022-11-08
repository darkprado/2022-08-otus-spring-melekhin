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
import ru.otus.example.springmongo.domain.Comment;
import ru.otus.example.springmongo.domain.Genre;
import ru.otus.example.springmongo.service.CommentService;
import ru.otus.example.springmongo.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author s.melekhin
 * @since 02 нояб. 2022 г.
 */
@SpringBootTest
public class CommentServiceImplTest {

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
    private CommentService commentService;

    private Comment expected;

    @BeforeEach
    public void setUp() {
        expected = new Comment("1", "C1", new Book("1", "B1", new Author("1", "A1", "A1"), new Genre("1", "G1")));
    }

    @Test
    void save() {
        when(commentRepository.save(any())).thenReturn(expected);
        when(bookRepository.findById("1")).thenReturn(Optional.ofNullable(new Book("1" , "B1", null, null)));
        String actual = commentService.save("C1", "1");
        assertEquals("1", actual);
    }

    @Test
    void getAllByBook() {
        when(commentRepository.findAllByBookId("1")).thenReturn(Collections.singletonList(expected));
        List<Comment> allComment = commentService.findAllByBook("1");
        assertEquals(1, allComment.size());
        assertEquals(expected, allComment.get(0));
    }

    @Test
    void getById() {
        when(commentRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        Comment actual = commentService.findById("1");
        assertEquals(expected, actual);
    }

    @Test
    void update() {
        when(commentRepository.save(any())).thenReturn(expected);
        when(commentRepository.findById("1")).thenReturn(Optional.ofNullable(expected));
        String actual = commentService.update("1", "C1");
        assertEquals("1", actual);
    }

}
