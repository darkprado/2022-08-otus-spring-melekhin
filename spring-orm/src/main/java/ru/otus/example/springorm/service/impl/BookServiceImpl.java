package ru.otus.example.springorm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springorm.dao.BookDao;
import ru.otus.example.springorm.domain.Author;
import ru.otus.example.springorm.domain.Book;
import ru.otus.example.springorm.domain.Genre;
import ru.otus.example.springorm.service.BookService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookDao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        return bookDao.getById(id).orElse(null);
    }

    @Override
    @Transactional
    public long save(String name, Author author, Genre genre) {
        return bookDao.insert(new Book(0, name, author, genre));
    }

    @Override
    @Transactional
    public long deleteById(long id) {
        return bookDao.deleteById(id);
    }

    @Override
    @Transactional
    public long update(long id, String name, Author author, Genre genre) {
        return bookDao.update(new Book(id, name, author, genre));
    }
}
