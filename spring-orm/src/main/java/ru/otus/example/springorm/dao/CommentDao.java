package ru.otus.example.springorm.dao;

import java.util.List;
import java.util.Optional;

import ru.otus.example.springorm.domain.Comment;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
public interface CommentDao {

    long insert(Comment comment);

    long update(Comment comment);

    Optional<Comment> getById(long id);

    List<Comment> getAllByBook(long bookId);

    long deleteById(long id);

}
