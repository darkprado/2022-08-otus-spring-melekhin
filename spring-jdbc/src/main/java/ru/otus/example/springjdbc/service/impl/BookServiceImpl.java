package ru.otus.example.springjdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.dao.AuthorDao;
import ru.otus.example.springjdbc.dao.BookDao;
import ru.otus.example.springjdbc.dao.GenreDao;
import ru.otus.example.springjdbc.domain.Author;
import ru.otus.example.springjdbc.domain.Book;
import ru.otus.example.springjdbc.domain.Genre;
import ru.otus.example.springjdbc.service.BookService;
import ru.otus.example.springjdbc.utils.IOService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;
    private final IOService ioService;

    @Override
    public void findAll() {
        List<Book> books = bookDao.getAll();
        ioService.out("Список книг:");
        books.forEach(book -> ioService.out(book.toString()));
    }

    @Override
    public void findById(long id) {
        Book book = bookDao.getById(id).orElse(null);
        if (book == null) {
            ioService.out(String.format("Книга с id = %s не найдена", id));
            findAll();
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
        Author author = authorDao.getById(Long.parseLong(authorId)).orElse(null);
        ioService.out("Введите id жанра");
        String genreId = ioService.in();
        Genre genre = genreDao.getById(Long.parseLong(genreId)).orElse(null);
        if (author == null) {
            ioService.out(String.format("Автор c id = %s не найден", authorId));
        } else if (genre == null) {
            ioService.out(String.format("Жанр с id = %s не найден", genreId));
        } else {
            bookDao.insert(new Book(name, author, genre));
            ioService.out("Книга сохранена");
        }
    }

    @Override
    public void deleteById(long id) {
        if (bookDao.deleteById(id) == 1) {
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
        Author author = authorDao.getById(Long.parseLong(authorId)).orElse(null);
        ioService.out("Введите id жанра");
        String genreId = ioService.in();
        Genre genre = genreDao.getById(Long.parseLong(genreId)).orElse(null);
        if (author == null) {
            ioService.out(String.format("Автор c id = %s не найден", authorId));
        } else if (genre == null) {
            ioService.out(String.format("Жанр с id = %s не найден", genreId));
        } else {
            bookDao.update(new Book(id, name, author, genre));
            ioService.out("Книга сохранена");
        }
    }
}
