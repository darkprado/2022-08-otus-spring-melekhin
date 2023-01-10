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
import ru.otus.springwebspa.springwebspa.domain.Comment;
import ru.otus.springwebspa.springwebspa.domain.Genre;
import ru.otus.springwebspa.springwebspa.dto.AuthorDto;
import ru.otus.springwebspa.springwebspa.dto.BookDto;
import ru.otus.springwebspa.springwebspa.dto.CommentDto;
import ru.otus.springwebspa.springwebspa.dto.GenreDto;
import ru.otus.springwebspa.springwebspa.mapper.CommentMapper;
import ru.otus.springwebspa.springwebspa.service.CommentService;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author s.melekhin
 * @since 14 дек. 2022 г.
 */
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CommentMapper commentMapper;
    @MockBean
    private CommentService commentService;
    private Comment expected;
    private CommentDto expectedDto;

    @BeforeEach
    public void setUp() {
        Author author = new Author(1L, "A1", "A1");
        Genre genre = new Genre(1L, "G1");
        Book book = new Book(1L, "B1", author, genre);
        expected = new Comment(1L, "C1", book);
        AuthorDto authorDto = new AuthorDto();
        authorDto.setFirstname("A1");
        authorDto.setLastname("A1");
        authorDto.setId(1L);
        GenreDto genreDto = new GenreDto();
        genreDto.setId(1L);
        genreDto.setName("G1");
        BookDto bookDto = new BookDto();
        bookDto.setId(1L);
        bookDto.setName("B1");
        bookDto.setAuthor(authorDto);
        bookDto.setGenre(genreDto);
        expectedDto = new CommentDto();
        expectedDto.setBook(bookDto);
    }

    @Test
    void save() throws Exception {
        when(commentService.save(anyString(), anyLong())).thenReturn(1L);
        when(commentMapper.toDomain(expectedDto)).thenReturn(expected);
        when(commentMapper.toDto(expected)).thenReturn(expectedDto);
        mvc.perform(post("/comment")
                        .content(objectMapper.writeValueAsString(expectedDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(1L)));
    }

    @Test
    void all() throws Exception {
        when(commentService.findAllByBook(1L)).thenReturn(List.of(expected));
        when(commentMapper.toDomain(expectedDto)).thenReturn(expected);
        when(commentMapper.toDto(expected)).thenReturn(expectedDto);
        mvc.perform(get("/comment/all/1"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        expected.setId(1L);
        when(commentService.update(anyLong(), anyString())).thenReturn(1L);
        when(commentMapper.toDomain(any())).thenReturn(expected);
        when(commentMapper.toDto(expected)).thenReturn(expectedDto);
        expectedDto.setId(1L);
        mvc.perform(put("/comment")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(1L)));
    }

    @Test
    void deleteComment() throws Exception {
        expected.setId(1L);
        doNothing().when(commentService).deleteById(1L);
        mvc.perform(delete("/comment/1")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
