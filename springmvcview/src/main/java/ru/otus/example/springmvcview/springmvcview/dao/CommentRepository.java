package ru.otus.example.springmvcview.springmvcview.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.otus.example.springmvcview.springmvcview.domain.Comment;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBookId(long bookId);

}
