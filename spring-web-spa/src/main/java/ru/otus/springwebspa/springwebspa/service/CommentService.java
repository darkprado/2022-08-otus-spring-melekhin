package ru.otus.springwebspa.springwebspa.service;

import java.util.List;

import ru.otus.springwebspa.springwebspa.domain.Comment;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
public interface CommentService {

    List<Comment> findAllByBook(long bookId);

    Comment findById(long id);

    long save(String text, long bookId);

    void deleteById(long id);

    long update(long id, String text);

}
