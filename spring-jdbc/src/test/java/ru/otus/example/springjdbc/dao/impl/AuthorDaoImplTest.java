package ru.otus.example.springjdbc.dao.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import ru.otus.example.springjdbc.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author s.melekhin
 * @since 17 окт. 2022 г.
 */
@JdbcTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDaoImpl authorDao;

    @Test
    void getAll() {
        List<Author> all = authorDao.getAll();
        assertThat(all).contains(new Author(1, "A1", "A1"));
        assertThat(all).contains(new Author(2, "A2", "A2"));
        assertThat(all).contains(new Author(3, "A3", "A3"));
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void getById() {
        Author author = authorDao.getById(1).orElse(null);
        assert author != null;
        assertThat(author.getFirstname()).isEqualTo("A1");
        assertThat(author.getLastname()).isEqualTo("A1");
    }

    @Test
    void deleteById() {
        long before = authorDao.getAll().size();
        authorDao.deleteById(3);
        long after = authorDao.getAll().size();
        assertThat(before).isGreaterThan(after);
    }

    @Test
    void insert() {
        Author author = new Author("test", "test");
        long before = authorDao.getAll().size();
        long id = authorDao.insert(author);
        long after = authorDao.getAll().size();
        assertThat(before).isLessThan(after);
        assertThat(id).isEqualTo(4);
        Author authorById = authorDao.getById(id).orElse(null);
        assert authorById != null;
        assertSame(author.getFirstname(), authorById.getFirstname());
        assertSame(author.getLastname(), authorById.getLastname());
    }

    @Test
    void update() {
        Author author = new Author(2, "test", "test");
        authorDao.update(author);
        Author authorById = authorDao.getById(2).orElse(null);
        assert authorById != null;
        assertThat(authorById).isEqualTo(author);
    }

}
