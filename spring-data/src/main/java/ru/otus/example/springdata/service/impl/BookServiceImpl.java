package ru.otus.example.springdata.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springdata.dao.BookRepository;
import ru.otus.example.springdata.domain.Author;
import ru.otus.example.springdata.domain.Book;
import ru.otus.example.springdata.domain.Genre;
import ru.otus.example.springdata.service.BookService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public long save(String name, Author author, Genre genre) {
        return repository.save(new Book(0, name, author, genre)).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public long update(long id, String name, Author author, Genre genre) {
        return repository.save(new Book(id, name, author, genre)).getId();
    }
}
