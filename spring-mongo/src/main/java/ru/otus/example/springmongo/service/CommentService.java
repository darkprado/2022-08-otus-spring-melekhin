package ru.otus.example.springmongo.service;

import java.util.List;

import ru.otus.example.springmongo.domain.Comment;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
public interface CommentService {

    List<Comment> findAllByBook(String bookId);

    Comment findById(String id);

    String save(String text, String bookId);

    void deleteById(String id);

    String update(String id, String text);

}
