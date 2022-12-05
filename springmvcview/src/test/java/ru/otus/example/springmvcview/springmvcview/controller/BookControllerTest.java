package ru.otus.example.springmvcview.springmvcview.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ru.otus.example.springmvcview.springmvcview.domain.Author;
import ru.otus.example.springmvcview.springmvcview.domain.Book;
import ru.otus.example.springmvcview.springmvcview.domain.Genre;
import ru.otus.example.springmvcview.springmvcview.service.AuthorService;
import ru.otus.example.springmvcview.springmvcview.service.BookService;
import ru.otus.example.springmvcview.springmvcview.service.GenreService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private AuthorService authorService;

    private Book expected;
    private Author author;
    private Genre genre;

    @BeforeEach
    public void setUp() {
        author = new Author(1L, "Karl", "Marks");
        genre = new Genre(1L, "Roman");
        expected = new Book(1L, "test", author, genre);
    }


    @Test
    void addPage() throws Exception {
        mvc.perform(get("/book/add"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/add"));
    }

    @Test
    void addBook() throws Exception {
        mvc.perform(post("/book/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/all"));
    }

    @Test
    void allBooksPage() throws Exception {
        mvc.perform(get("/book/all"))
                .andExpect(model().attributeExists("books"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/list"));
    }

    @Test
    void editPage() throws Exception {
        when(bookService.findById(1L)).thenReturn(expected);
        mvc.perform(get("/book/edit")
                        .param("id", "1"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(model().attributeExists("book"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/edit"));
    }

    @Test
    void editBook() throws Exception {
        when(bookService.findById(1L)).thenReturn(expected);
        when(authorService.findById(anyLong())).thenReturn(author);
        when(genreService.findById(anyLong())).thenReturn(genre);
        mvc.perform(post("/book/edit")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/all"));
    }

    @Test
    void deletePage() throws Exception {
        when(bookService.findById(1L)).thenReturn(expected);
        mvc.perform(get("/book/delete")
                        .param("id", "1"))
                .andExpect(model().attributeExists("book"))
                .andExpect(status().isOk())
                .andExpect(view().name("book/delete"));
    }

    @Test
    void deleteBook() throws Exception {
        when(bookService.findById(1L)).thenReturn(expected);
        mvc.perform(post("/book/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/book/all"));
    }

}