package ru.otus.example.springorm.service;

import java.util.List;

import ru.otus.example.springorm.domain.Comment;
import ru.otus.example.springorm.domain.Genre;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
public interface CommentService {

    List<Comment> findAllByBook(long bookId);

    Comment findById(long id);

    long save(String text, long bookId);

    long deleteById(long id);

    long update(long id, String text);

}
