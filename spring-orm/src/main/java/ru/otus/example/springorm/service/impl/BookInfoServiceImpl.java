package ru.otus.example.springorm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springorm.domain.Author;
import ru.otus.example.springorm.domain.Book;
import ru.otus.example.springorm.domain.Genre;
import ru.otus.example.springorm.service.AuthorService;
import ru.otus.example.springorm.service.BookService;
import ru.otus.example.springorm.service.GenreService;
import ru.otus.example.springorm.service.InfoService;
import ru.otus.example.springorm.utils.IOService;

/**
 * @author s.melekhin
 * @since 19 окт. 2022 г.
 */
@Service("bookInfoService")
@RequiredArgsConstructor
public class BookInfoServiceImpl implements InfoService {

    private final IOService ioService;
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override
    public void getAllInfo() {
        List<Book> books = bookService.findAll();
        ioService.out("Список книг:");
        books.forEach(book -> ioService.out(book.toString()));
    }

    @Override
    public void getInfoById(long id) {
        Book book = bookService.findById(id);
        if (book == null) {
            ioService.out(String.format("Книга с id = %s не найдена", id));
            getAllInfo();
        } else {
            ioService.out(book.toString());
        }
    }

    @Override
    public void save() {
        ioService.out("Введите название книги");
        String name = ioService.in();
        ioService.out("Введите id автора");
        String authorId = ioService.in();
        Author author = authorService.findById(Long.parseLong(authorId));
        ioService.out("Введите id жанра");
        String genreId = ioService.in();
        Genre genre = genreService.findById(Long.parseLong(genreId));
        if (author == null) {
            ioService.out(String.format("Автор c id = %s не найден", authorId));
        } else if (genre == null) {
            ioService.out(String.format("Жанр с id = %s не найден", genreId));
        } else {
            bookService.save(name, author, genre);
            ioService.out("Книга сохранена");
        }
    }

    @Override
    public void deleteById(long id) {
        if (bookService.deleteById(id) == 1) {
            ioService.out("Книга удалена");
        } else {
            ioService.out(String.format("Книга c id = %s не найдена", id));
        }
    }

    @Override
    public void update(long id) {
        ioService.out("Введите название книги");
        String name = ioService.in();
        ioService.out("Введите id автора");
        String authorId = ioService.in();
        Author author = authorService.findById(Long.parseLong(authorId));
        ioService.out("Введите id жанра");
        String genreId = ioService.in();
        Genre genre = genreService.findById(Long.parseLong(genreId));
        if (author == null) {
            ioService.out(String.format("Автор c id = %s не найден", authorId));
        } else if (genre == null) {
            ioService.out(String.format("Жанр с id = %s не найден", genreId));
        } else {
            bookService.update(id, name, author, genre);
            ioService.out("Книга сохранена");
        }
    }
}
