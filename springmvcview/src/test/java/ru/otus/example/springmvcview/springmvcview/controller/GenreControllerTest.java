package ru.otus.example.springmvcview.springmvcview.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import ru.otus.example.springmvcview.springmvcview.domain.Genre;
import ru.otus.example.springmvcview.springmvcview.service.GenreService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GenreController.class)
class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService genreService;
    private Genre expected;

    @BeforeEach
    public void setUp() {
        expected = new Genre(1L, "Karl");
    }

    @Test
    void addPage() throws Exception {
        mvc.perform(get("/genre/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("genre/add"));
    }

    @Test
    void addGenre() throws Exception {
        mvc.perform(post("/genre/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/genre/all"));
    }

    @Test
    void allGenresPage() throws Exception {
        mvc.perform(get("/genre/all"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("genres"))
                .andExpect(view().name("genre/list"));
    }


    @Test
    void editPage() throws Exception {
        when(genreService.findById(1L)).thenReturn(expected);
        mvc.perform(get("/genre/edit")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("genre"))
                .andExpect(view().name("genre/edit"));
    }

    @Test
    void editGenre() throws Exception {
        when(genreService.findById(1L)).thenReturn(expected);
        mvc.perform(post("/genre/edit")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/genre/all"));
    }

    @Test
    void deletePage() throws Exception {
        when(genreService.findById(1L)).thenReturn(expected);
        mvc.perform(get("/genre/delete")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("genre"))
                .andExpect(view().name("genre/delete"));
    }

    @Test
    void deleteGenre() throws Exception {
        mvc.perform(post("/genre/delete")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/genre/all"));
    }
}