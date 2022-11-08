package ru.otus.example.springmongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.otus.example.springmongo.domain.Comment;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String bookId);

}
