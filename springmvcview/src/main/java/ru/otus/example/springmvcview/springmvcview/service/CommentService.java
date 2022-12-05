package ru.otus.example.springmvcview.springmvcview.service;

import java.util.List;

import ru.otus.example.springmvcview.springmvcview.domain.Comment;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
public interface CommentService {

    List<Comment> findAllByBook(long bookId);

    Comment findById(long id);

    long save(String text, long bookId);

    void deleteById(long id);

    long update(long id, String text);

}
