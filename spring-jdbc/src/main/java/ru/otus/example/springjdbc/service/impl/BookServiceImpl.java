package ru.otus.example.springjdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.dao.BookDao;
import ru.otus.example.springjdbc.domain.Author;
import ru.otus.example.springjdbc.domain.Book;
import ru.otus.example.springjdbc.domain.Genre;
import ru.otus.example.springjdbc.service.BookService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.getAll();
    }

    @Override
    public Book findById(long id) {
        return bookDao.getById(id).orElse(null);
    }

    @Override
    public long save(String name, Author author, Genre genre) {
        return bookDao.insert(new Book(name, author, genre));
    }

    @Override
    public long deleteById(long id) {
        return bookDao.deleteById(id);
    }

    @Override
    public long update(long id, String name, Author author, Genre genre) {
        return bookDao.update(new Book(id, name, author, genre));
    }
}
