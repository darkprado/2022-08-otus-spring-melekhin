package ru.otus.example.springorm.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springorm.dao.AuthorDao;
import ru.otus.example.springorm.domain.Author;

/**
 * @author s.melekhin
 * @since 11 окт. 2022 г.
 */
@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long insert(Author author) {
        if (author.getId() == 0) {
            em.persist(author);
            em.flush();
        } else {
            em.merge(author);
        }
        return author.getId();
    }

    @Override
    public long update(Author author) {
        return em.merge(author).getId();
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> getAll() {
        return em.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    public long deleteById(long id) {
        return em.createQuery("delete from Author a where a.id = :id").setParameter("id", id).executeUpdate();
    }

}
