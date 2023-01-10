package ru.otus.springwebspa.springwebspa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.otus.springwebspa.springwebspa.domain.Author;
import ru.otus.springwebspa.springwebspa.dto.AuthorDto;
import ru.otus.springwebspa.springwebspa.mapper.AuthorMapper;
import ru.otus.springwebspa.springwebspa.service.AuthorService;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @GetMapping("/author/all")
    public List<AuthorDto> getAll() {
        return authorMapper.toDto(authorService.findAll());
    }

    @GetMapping("/author/{id}")
    public AuthorDto getById(@PathVariable long id) {
        return authorMapper.toDto(authorService.findById(id));
    }

    @PostMapping("/author")
    public long save(@RequestBody AuthorDto authorDto) {
        Author author = authorMapper.toDomain(authorDto);
        return authorService.save(author.getFirstname(), author.getLastname());
    }

    @PutMapping("/author")
    public long update(@RequestBody AuthorDto authorDto) {
        Author author = authorMapper.toDomain(authorDto);
        return authorService.update(author.getId(), author.getFirstname(), author.getLastname());
    }

    @DeleteMapping("/author/{id}")
    public void delete(@PathVariable long id) {
        authorService.deleteById(id);
    }

}
