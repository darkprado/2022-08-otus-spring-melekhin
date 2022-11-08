package ru.otus.example.springmongo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmongo.dao.AuthorRepository;
import ru.otus.example.springmongo.dao.BookRepository;
import ru.otus.example.springmongo.dao.CommentRepository;
import ru.otus.example.springmongo.domain.Author;
import ru.otus.example.springmongo.service.AuthorService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(String id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public String save(String firstname, String lastname) {
        return authorRepository.save(new Author(firstname, lastname)).getId();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        authorRepository.deleteById(id);
        bookRepository.findAllByAuthorId(id).forEach(book -> {
            bookRepository.deleteById(book.getId());
            commentRepository.deleteAll(commentRepository.findAllByBookId(book.getId()));
        });
    }

    @Override
    @Transactional
    public String update(String id, String firstname, String lastname) {
        Author author = findById(id);
        if (author == null) {
            return null;
        }
        author.setFirstname(firstname);
        author.setLastname(lastname);
        return authorRepository.save(author).getId();
    }

}
