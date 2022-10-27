package ru.otus.example.springorm.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springorm.dao.GenreDao;
import ru.otus.example.springorm.domain.Genre;

/**
 * @author s.melekhin
 * @since 11 окт. 2022 г.
 */
@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<Genre> getAll() {
        return em.createQuery("select g from Genre g", Genre.class)
                .getResultList();
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public long insert(Genre genre) {
        if (genre.getId() == 0) {
            em.persist(genre);
            em.flush();
        } else {
            em.merge(genre);
        }
        return genre.getId();
    }

    @Override
    public long update(Genre genre) {
        return em.merge(genre).getId();
    }

    @Override
    public long deleteById(long id) {
        return em.createQuery("delete from Genre g where g.id = :id").setParameter("id", id).executeUpdate();
    }

}
