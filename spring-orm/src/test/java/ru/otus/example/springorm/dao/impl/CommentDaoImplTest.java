package ru.otus.example.springorm.dao.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import ru.otus.example.springorm.domain.Author;
import ru.otus.example.springorm.domain.Book;
import ru.otus.example.springorm.domain.Comment;
import ru.otus.example.springorm.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author s.melekhin
 * @since 25 окт. 2022 г.
 */
@DataJpaTest
@Import(CommentDaoImpl.class)
public class CommentDaoImplTest {

    @Autowired
    private CommentDaoImpl commentDao;

    @Test
    void getAllByBook() {
        List<Comment> all = commentDao.getAllByBook(1);
        assertEquals(all.get(0).getComment(), "C1");
        assertEquals(all.get(1).getComment(), "C2");
        assertThat(all.size()).isEqualTo(2);
    }

    @Test
    void getById() {
        Comment comment = commentDao.getById(1).orElse(null);
        assert comment != null;
        assertThat(comment.getComment()).isEqualTo("C1");
    }

    @Test
    void deleteById() {
        long before = commentDao.getAllByBook(1).size();
        commentDao.deleteById(1);
        long after = commentDao.getAllByBook(1).size();
        assertThat(before).isGreaterThan(after);
    }

    @Test
    void insert() {
        Comment comment = new Comment(0, "test",
                new Book(1, "B1",
                        new Author(1, "A1", "A1"),
                        new Genre(1, "G1")));
        long before = commentDao.getAllByBook(1).size();
        long id = commentDao.insert(comment);
        long after = commentDao.getAllByBook(1).size();
        assertThat(before).isLessThan(after);
        assertThat(id).isEqualTo(5);
        Comment commentById = commentDao.getById(id).orElse(null);
        assert commentById != null;
        assertSame(comment.getComment(), commentById.getComment());
    }

    @Test
    void update() {
        Comment comment = new Comment(1, "test",
                new Book(1, "B1",
                        new Author(1, "A1", "A1"),
                        new Genre(1, "G1")));
        commentDao.update(comment);
        Comment commentById = commentDao.getById(1).orElse(null);
        assert commentById != null;
        assertThat(commentById).isEqualTo(comment);
    }

}
