package ru.otus.example.springmongo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmongo.dao.BookRepository;
import ru.otus.example.springmongo.dao.CommentRepository;
import ru.otus.example.springmongo.domain.Author;
import ru.otus.example.springmongo.domain.Book;
import ru.otus.example.springmongo.domain.Genre;
import ru.otus.example.springmongo.service.BookService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public String save(String name, Author author, Genre genre) {
        return bookRepository.save(new Book(name, author, genre)).getId();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        bookRepository.deleteById(id);
        commentRepository.deleteAll(commentRepository.findAllByBookId(id));
    }

    @Override
    @Transactional
    public String update(String id, String name, Author author, Genre genre) {
        Book book = findById(id);
        if (book == null) {
            return null;
        }
        book.setName(name);
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.save(book).getId();
    }
}
