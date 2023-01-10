package ru.otus.springwebspa.springwebspa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.otus.springwebspa.springwebspa.domain.Genre;
import ru.otus.springwebspa.springwebspa.dto.GenreDto;
import ru.otus.springwebspa.springwebspa.mapper.GenreMapper;
import ru.otus.springwebspa.springwebspa.service.GenreService;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;
    private final GenreMapper genreMapper;

    @GetMapping("/genre/all")
    public List<GenreDto> getAll() {
        return genreMapper.toDto(genreService.findAll());
    }

    @GetMapping("/genre/{id}")
    public GenreDto getById(@PathVariable long id) {
        return genreMapper.toDto(genreService.findById(id));
    }

    @PostMapping("/genre")
    public long save(@RequestBody GenreDto genreDto) {
        Genre genre = genreMapper.toDomain(genreDto);
        return genreService.save(genre.getName());
    }

    @PutMapping("/genre")
    public long update(@RequestBody GenreDto genreDto) {
        Genre genre = genreMapper.toDomain(genreDto);
        return genreService.update(genre.getId(), genre.getName());
    }

    @DeleteMapping("/genre/{id}")
    public void delete(@PathVariable long id) {
        genreService.deleteById(id);
    }

}
