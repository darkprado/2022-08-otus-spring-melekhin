package ru.otus.example.springdata.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import ru.otus.example.springdata.domain.Author;
import ru.otus.example.springdata.domain.Book;
import ru.otus.example.springdata.domain.Comment;
import ru.otus.example.springdata.domain.Genre;

/**
 * @author s.melekhin
 * @since 02 нояб. 2022 г.
 */
@DataJpaTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findByBookId() {
        List<Comment> commentsByBookId = commentRepository.findByBookId(1);
        assert !commentsByBookId.isEmpty();
        Assertions.assertEquals(2, commentsByBookId.size());
        Comment commentExpected1 = new Comment(1L, "C1", new Book(1, "B1", new Author(1, "A1", "A1"), new Genre(1, "G1")));
        Comment commentExpected2 = new Comment(2L, "C2", new Book(1, "B1", new Author(1, "A1", "A1"), new Genre(1, "G1")));
        Assertions.assertEquals(commentExpected1, commentsByBookId.get(0));
        Assertions.assertEquals(commentExpected2, commentsByBookId.get(1));
    }

}
