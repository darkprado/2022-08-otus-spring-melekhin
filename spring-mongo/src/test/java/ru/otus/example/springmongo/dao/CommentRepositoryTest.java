package ru.otus.example.springmongo.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import ru.otus.example.springmongo.domain.Comment;

/**
 * @author s.melekhin
 * @since 02 нояб. 2022 г.
 */
@DataMongoTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findByBookId() {
        List<Comment> commentsByBookId = commentRepository.findAllByBookId("1");
        assert !commentsByBookId.isEmpty();
        Assertions.assertEquals(4, commentsByBookId.size());
    }

}
