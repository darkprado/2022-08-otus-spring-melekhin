package ru.otus.example.springjdbc.dao.impl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import ru.otus.example.springjdbc.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author s.melekhin
 * @since 17 окт. 2022 г.
 */
@JdbcTest
@Import(GenreDaoImpl.class)
public class GenreDaoImplTest {

    @Autowired
    private GenreDaoImpl genreDao;

    @Test
    void getAll() {
        List<Genre> all = genreDao.getAll();
        assertThat(all).contains(new Genre(1, "G1"));
        assertThat(all).contains(new Genre(2, "G2"));
        assertThat(all).contains(new Genre(3, "G3"));
        assertThat(all.size()).isEqualTo(3);
    }

    @Test
    void getById() {
        Genre genre = genreDao.getById(1).orElse(null);
        assert genre != null;
        assertThat(genre.getName()).isEqualTo("G1");
    }

    @Test
    void deleteById() {
        long before = genreDao.getAll().size();
        genreDao.deleteById(3);
        long after = genreDao.getAll().size();
        assertThat(before).isGreaterThan(after);
    }

    @Test
    void insert() {
        Genre genre = new Genre("test");
        long before = genreDao.getAll().size();
        long id = genreDao.insert(genre);
        long after = genreDao.getAll().size();
        assertThat(before).isLessThan(after);
        assertThat(id).isEqualTo(4);
        Genre genreById = genreDao.getById(id).orElse(null);
        assert genreById != null;
        assertSame(genre.getName(), genreById.getName());
    }

    @Test
    void update() {
        Genre genre = new Genre(2, "test");
        genreDao.update(genre);
        Genre genreById = genreDao.getById(2).orElse(null);
        assert genreById != null;
        assertThat(genreById).isEqualTo(genre);
    }

}
