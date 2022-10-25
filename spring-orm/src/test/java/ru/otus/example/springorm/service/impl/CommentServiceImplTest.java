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
import ru.otus.example.springorm.dao.CommentDao;
import ru.otus.example.springorm.dao.GenreDao;
import ru.otus.example.springorm.domain.Author;
import ru.otus.example.springorm.domain.Book;
import ru.otus.example.springorm.domain.Comment;
import ru.otus.example.springorm.domain.Genre;
import ru.otus.example.springorm.service.AuthorService;
import ru.otus.example.springorm.service.BookService;
import ru.otus.example.springorm.service.CommentService;
import ru.otus.example.springorm.service.GenreService;
import ru.otus.example.springorm.utils.IOService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author s.melekhin
 * @since 25 окт. 2022 г.
 */
@SpringBootTest()
public class CommentServiceImplTest {

    @MockBean
    private IOService ioService;

    @MockBean
    private AuthorDao authorDao;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private GenreDao genreDao;

    @MockBean
    private CommentDao commentDao;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @Autowired
    private CommentService commentService;

    private Comment expected;

    @BeforeEach
    public void setUp() {
        expected = new Comment(0, "test",
                new Book(0, "name", new Author(1, "firstname", "lastname"), new Genre(1, "genre")));
    }

    @Test
    void save() {
        when(commentDao.insert(any())).thenReturn(1L);
        when(commentDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = commentService.save("test", 1);
        assertEquals(1L, actual);
    }

    @Test
    void getAllByBook() {
        when(commentDao.getAllByBook(1)).thenReturn(Collections.singletonList(expected));
        List<Comment> allCommentByBook = commentService.findAllByBook(1);
        assertEquals(1, allCommentByBook.size());
        assertEquals(expected, allCommentByBook.get(0));
    }

    @Test
    void getById() {
        when(commentDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        Comment comment = commentService.findById(1L);
        assertEquals(expected, comment);
    }

    @Test
    void update() {
        expected.setId(1L);
        when(commentDao.update(expected)).thenReturn(1L);
        when(commentDao.getById(1L)).thenReturn(Optional.ofNullable(expected));
        long actual = commentService.update(1L, "test");
        assertEquals(1L, actual);
    }

    @Test
    void deleteById() {
        when(commentDao.deleteById(1L)).thenReturn(1L);
        long actual = commentService.deleteById(1L);
        assertEquals(1, actual);
    }

}
