package ru.otus.springwebspa.springwebspa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.springwebspa.springwebspa.domain.Comment;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBookId(long bookId);

}
