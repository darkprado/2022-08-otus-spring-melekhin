package ru.otus.example.springorm.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springorm.dao.CommentDao;
import ru.otus.example.springorm.domain.Comment;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
@Repository
@RequiredArgsConstructor
public class CommentDaoImpl implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public long insert(Comment comment) {
        if (comment.getId() == 0) {
            em.persist(comment);
            em.flush();
        } else {
            em.merge(comment);
        }
        return comment.getId();
    }

    @Override
    public long update(Comment comment) {
        return em.merge(comment).getId();
    }

    @Override
    public Optional<Comment> getById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> getAllByBook(long bookId) {
        EntityGraph<?> entityGraph = em.getEntityGraph("comment-graph");
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.book.id = :id", Comment.class).setParameter("id", bookId);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

    @Override
    public long deleteById(long id) {
        return em.createQuery("delete from Comment c where c.id = :id").setParameter("id", id).executeUpdate();
    }

}
