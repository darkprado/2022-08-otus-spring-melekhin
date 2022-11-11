package ru.otus.example.springmongo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmongo.dao.BookRepository;
import ru.otus.example.springmongo.dao.CommentRepository;
import ru.otus.example.springmongo.dao.GenreRepository;
import ru.otus.example.springmongo.domain.Genre;
import ru.otus.example.springmongo.service.GenreService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(String id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public String save(String name) {
        return genreRepository.save(new Genre(name)).getId();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        genreRepository.deleteById(id);
        bookRepository.findAllByGenreId(id).forEach(book -> {
            bookRepository.deleteById(book.getId());
            commentRepository.deleteAll(commentRepository.findAllByBookId(book.getId()));
        });
    }

    @Override
    @Transactional
    public String update(String id, String name) {
        Genre genre = findById(id);
        if (genre == null) {
            return null;
        }
        genre.setName(name);
        return genreRepository.save(new Genre(id, name)).getId();
    }
}
