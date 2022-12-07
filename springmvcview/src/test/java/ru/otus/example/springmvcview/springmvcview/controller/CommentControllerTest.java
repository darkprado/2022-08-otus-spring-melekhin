package ru.otus.example.springmvcview.springmvcview.controller;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ru.otus.example.springmvcview.springmvcview.domain.Author;
import ru.otus.example.springmvcview.springmvcview.domain.Book;
import ru.otus.example.springmvcview.springmvcview.domain.Comment;
import ru.otus.example.springmvcview.springmvcview.domain.Genre;
import ru.otus.example.springmvcview.springmvcview.service.BookService;
import ru.otus.example.springmvcview.springmvcview.service.CommentService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @MockBean
    private BookService bookService;
    private Comment expected;

    @BeforeEach
    public void setUp() {
        Author author = new Author(1L, "Karl", "Marks");
        Genre genre = new Genre(1L, "Roman");
        Book book = new Book(1L, "test", author, genre);
        expected = new Comment(1L, "Karl", book);
    }

    @Test
    void addPage() throws Exception {
        mvc.perform(get("/comment/add")
                        .param("bookId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("comment/add"));
    }

    @Test
    void addComment() throws Exception {
        mvc.perform(post("/comment/add")
                        .param("bookId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/all"));
    }

    @Test
    void allCommentsPage() throws Exception {
        when(commentService.findAllByBook(1L)).thenReturn(Collections.singletonList(expected));
        mvc.perform(get("/comment/all/book")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("comment/list"));
    }

    @Test
    void editComment() throws Exception {
        when(commentService.findById(1L)).thenReturn(expected);
        mvc.perform(post("/comment/edit")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/all"));
    }

    @Test
    void deletePage() throws Exception {
        when(commentService.findById(1L)).thenReturn(expected);
        mvc.perform(get("/comment/delete")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("comment"))
                .andExpect(view().name("comment/delete"));
    }

    @Test
    void deleteComment() throws Exception {
        mvc.perform(post("/comment/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/all"));
    }
}