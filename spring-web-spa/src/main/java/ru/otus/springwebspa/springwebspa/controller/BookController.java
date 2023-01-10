package ru.otus.springwebspa.springwebspa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.otus.springwebspa.springwebspa.domain.Book;
import ru.otus.springwebspa.springwebspa.dto.BookDto;
import ru.otus.springwebspa.springwebspa.mapper.BookMapper;
import ru.otus.springwebspa.springwebspa.service.BookService;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/book/all")
    public List<BookDto> getAll() {
        return bookMapper.toDto(bookService.findAll());
    }

    @GetMapping("/book/{id}")
    public BookDto getById(@PathVariable long id) {
        return bookMapper.toDto(bookService.findById(id));
    }

    @PostMapping("/book")
    public long save(@RequestBody BookDto bookDto) {
        Book book = bookMapper.toDomain(bookDto);
        return bookService.save(book.getName(), book.getAuthor(), book.getGenre());
    }

    @PutMapping("/book")
    public long update(@RequestBody BookDto bookDto) {
        Book book = bookMapper.toDomain(bookDto);
        return bookService.update(book.getId(), book.getName(), book.getAuthor(), book.getGenre());
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable long id) {
        bookService.deleteById(id);
    }

}
