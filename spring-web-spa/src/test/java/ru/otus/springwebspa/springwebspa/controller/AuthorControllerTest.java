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
import ru.otus.springwebspa.springwebspa.dto.AuthorDto;
import ru.otus.springwebspa.springwebspa.mapper.AuthorMapper;
import ru.otus.springwebspa.springwebspa.service.AuthorService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author s.melekhin
 * @since 14 дек. 2022 г.
 */
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService authorService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AuthorMapper authorMapper;
    private Author expected;
    private AuthorDto expectedDto;

    @BeforeEach
    public void setUp() {
        expected = new Author(1L, "A1", "A1");
        expectedDto = new AuthorDto();
        expectedDto.setId(1L);
        expectedDto.setFirstname("A1");
        expectedDto.setLastname("A1");
    }

    @Test
    void save() throws Exception {
        when(authorService.save(anyString(), anyString())).thenReturn(1L);
        when(authorMapper.toDomain(expectedDto)).thenReturn(expected);
        when(authorMapper.toDto(expected)).thenReturn(expectedDto);
        mvc.perform(post("/author")
                        .content(objectMapper.writeValueAsString(expectedDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void all() throws Exception {
        when(authorService.findAll()).thenReturn(List.of(expected));
        when(authorMapper.toDomain(expectedDto)).thenReturn(expected);
        when(authorMapper.toDto(expected)).thenReturn(expectedDto);
        mvc.perform(get("/author/all"))
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        expected.setId(1L);
        when(authorService.save(anyString(), anyString())).thenReturn(1L);
        when(authorMapper.toDomain(expectedDto)).thenReturn(expected);
        when(authorMapper.toDto(expected)).thenReturn(expectedDto);
        expectedDto.setId(1L);
        mvc.perform(put("/author")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAuthor() throws Exception {
        expected.setId(1L);
        doNothing().when(authorService).deleteById(expected.getId());
        mvc.perform(delete("/author/1")
                        .content(objectMapper.writeValueAsString(expected))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
