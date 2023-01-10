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

import ru.otus.springwebspa.springwebspa.domain.Genre;
import ru.otus.springwebspa.springwebspa.dto.GenreDto;
import ru.otus.springwebspa.springwebspa.mapper.GenreMapper;
import ru.otus.springwebspa.springwebspa.service.GenreService;

import static org.mockito.ArgumentMatchers.anyLong;
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
@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private GenreMapper genreMapper;
    @MockBean
    private GenreService genreService;
    private Genre expected;
    private GenreDto expectedDto;

    @BeforeEach
    public void setUp() {
        expected = new Genre(1L, "G1");
        expectedDto = new GenreDto();
        expectedDto.setId(1L);
        expectedDto.setName("G1");
    }

    @Test
    void save() throws Exception {
        when(genreService.save(anyString())).thenReturn(1L);
        when(genreMapper.toDomain(expectedDto)).thenReturn(expected);
        when(genreMapper.toDto(expected)).thenReturn(expectedDto);
        mvc.perform(post("/genre")
                        .content(objectMapper.writeValueAsString(expectedDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(1L)));
    }

    @Test
    void all() throws Exception {
        when(genreService.findAll()).thenReturn(List.of(expected));
        when(genreMapper.toDto(List.of(expected))).thenReturn(List.of(expectedDto));
        mvc.perform(get("/genre/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        List.of(expectedDto))));
    }

    @Test
    void update() throws Exception {
        expected.setId(1L);
        when(genreService.update(anyLong(), anyString())).thenReturn(1L);
        when(genreMapper.toDomain(expectedDto)).thenReturn(expected);
        when(genreMapper.toDto(expected)).thenReturn(expectedDto);
        expectedDto.setId(1L);
        mvc.perform(put("/genre")
                        .content(objectMapper.writeValueAsString(expectedDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        objectMapper.writeValueAsString(1L)));
    }

    @Test
    void deleteGenre() throws Exception {
        doNothing().when(genreService).deleteById(1L);
        mvc.perform(delete("/genre/1"))
                .andExpect(status().isOk());
    }

}
