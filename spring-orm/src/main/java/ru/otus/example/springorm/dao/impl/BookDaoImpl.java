package ru.otus.example.springorm.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import ru.otus.example.springorm.dao.BookDao;
import ru.otus.example.springorm.domain.Book;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
@Repository
@AllArgsConstructor
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long insert(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
            em.flush();
        } else {
            em.merge(book);
        }
        return book.getId();
    }

    @Override
    public long update(Book book) {
        return em.merge(book).getId();
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("book-graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public long deleteById(long id) {
        return em.createQuery("delete from Book b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

}
