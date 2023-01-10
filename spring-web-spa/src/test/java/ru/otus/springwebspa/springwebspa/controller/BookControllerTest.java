package ru.otus.springwebspa.springwebspa.controller;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.otus.springwebspa.springwebspa.domain.Author;
import ru.otus.springwebspa.springwebspa.domain.Book;
import ru.otus.springwebspa.springwebspa.domain.Genre;
import ru.otus.springwebspa.springwebspa.dto.AuthorDto;
import ru.otus.springwebspa.springwebspa.dto.BookDto;
import ru.otus.springwebspa.springwebspa.dto.GenreDto;
import ru.otus.springwebspa.springwebspa.mapper.BookMapper;
import ru.otus.springwebspa.springwebspa.service.BookService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author s.melekhin
 * @since 14 дек. 2022 г.
 */
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private BookService bookService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private BookMapper bookMapper;
    private Book expected;
    private BookDto expectedDto;
    private Author author;
    private Genre genre;

    @BeforeEach
    public void setUp() {
        author = new Author(1L, "A1", "A1");
        genre = new Genre(1L, "G1");
        expected = new Book(1L, "B1", author, genre);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstname("A1");
        authorDto.setLastname("A1");
        authorDto.setId(1L);
        GenreDto genreDto = new GenreDto();
        genreDto.setId(1L);
        genreDto.setName("G1");
        expectedDto = new BookDto();
        expectedDto.setId(1L);
        expectedDto.setName("B1");
        expectedDto.setAuthor(authorDto);
        expectedDto.setGenre(genreDto);
    }

    @Test
    void save() throws Exception {
        when(bookService.save(anyString(), any(), any())).thenReturn(1L);
        when(bookMapper.toDomain(expectedDto)).thenReturn(expected);
        when(bookMapper.toDto(expected)).thenReturn(expectedDto);
        mvc.perform(post("/book")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(1L)));
    }

    @Test
    void all() throws Exception {
        when(bookService.findAll()).thenReturn(List.of(expected));
        when(bookMapper.toDomain(expectedDto)).thenReturn(expected);
        when(bookMapper.toDto(expected)).thenReturn(expectedDto);
        mvc.perform(get("/book/all"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        when(bookService.findById(1L)).thenReturn(expected);
        when(bookService.save(anyString(), any(), any())).thenReturn(1L);
        when(bookMapper.toDomain(expectedDto)).thenReturn(expected);
        when(bookMapper.toDto(expected)).thenReturn(expectedDto);
        mvc.perform(put("/book")
                        .content(objectMapper.writeValueAsString(expectedDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteBook() throws Exception {
        doNothing().when(bookService).deleteById(1L);
        mvc.perform(delete("/book/1")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
