package ru.otus.example.springmvcview.springmvcview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmvcview.springmvcview.domain.Author;
import ru.otus.example.springmvcview.springmvcview.domain.Book;
import ru.otus.example.springmvcview.springmvcview.domain.Genre;
import ru.otus.example.springmvcview.springmvcview.dto.AuthorDto;
import ru.otus.example.springmvcview.springmvcview.dto.BookDto;
import ru.otus.example.springmvcview.springmvcview.service.AuthorService;
import ru.otus.example.springmvcview.springmvcview.service.BookService;
import ru.otus.example.springmvcview.springmvcview.service.GenreService;

/**
 * @author s.melekhin
 * @since 12 нояб. 2022 г.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping("/all")
    public String listPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        List<Genre> genres = genreService.findAll();
        List<Author> authors = authorService.findAll();
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "book/edit";
    }

    @PostMapping("/edit")
    public String updateBook(BookDto bookDto) {
        bookService.update(
                bookDto.getId(), bookDto.getName(),
                authorService.findById(bookDto.getAuthor()),
                genreService.findById(bookDto.getGenre()));
        return "redirect:/book/all";
    }

    @GetMapping("/delete")
    public String getDeletePage(@RequestParam("id") int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "book/delete";
    }

    @PostMapping("/delete")
    public String deleteBook(Book book) {
        bookService.deleteById(book.getId());
        return "redirect:/book/all";
    }

    @PostMapping("/add")
    public String add(BookDto bookDto) {
        bookService.save(
                bookDto.getName(),
                authorService.findById(bookDto.getAuthor()),
                genreService.findById(bookDto.getGenre()));
        return "redirect:/book/all";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        List<Genre> genres = genreService.findAll();
        List<Author> authors = authorService.findAll();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        return "book/add";
    }

}
