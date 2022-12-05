package ru.otus.example.springmvcview.springmvcview.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ru.otus.example.springmvcview.springmvcview.domain.Author;
import ru.otus.example.springmvcview.springmvcview.service.AuthorService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;
    private Author expected;

    @BeforeEach
    public void setUp() {
        expected = new Author(1L, "Karl", "Marks");
    }

    @Test
    void addPage() throws Exception {
        mvc.perform(get("/author/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("author/add"));
    }

    @Test
    void addAuthor() throws Exception {
        mvc.perform(post("/author/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/author/all"));
    }

    @Test
    void allAuthorsPage() throws Exception {
        mvc.perform(get("/author/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("authors"))
                .andExpect(view().name("author/list"));
    }


    @Test
    void editPage() throws Exception {
        when(authorService.findById(1L)).thenReturn(expected);
        mvc.perform(get("/author/edit")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("author"))
                .andExpect(view().name("author/edit"));
    }

    @Test
    void editAuthor() throws Exception {
        when(authorService.findById(1L)).thenReturn(expected);
        mvc.perform(post("/author/edit")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/author/all"));
    }

    @Test
    void deletePage() throws Exception {
        when(authorService.findById(1L)).thenReturn(expected);
        mvc.perform(get("/author/delete")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("author"))
                .andExpect(view().name("author/delete"));
    }

    @Test
    void deleteAuthor() throws Exception {
        mvc.perform(post("/author/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/author/all"));
    }
}