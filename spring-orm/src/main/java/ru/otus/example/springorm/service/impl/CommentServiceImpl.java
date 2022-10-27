package ru.otus.example.springorm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springorm.dao.CommentDao;
import ru.otus.example.springorm.domain.Comment;
import ru.otus.example.springorm.service.BookService;
import ru.otus.example.springorm.service.CommentService;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDao dao;
    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAllByBook(long bookId) {
        return dao.getAllByBook(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(long id) {
        return dao.getById(id).orElse(null);
    }

    @Override
    @Transactional
    public long save(String text, long bookId) {
        return dao.insert(new Comment(0, text, bookService.findById(bookId)));
    }

    @Override
    @Transactional
    public long deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional
    public long update(long id, String text) {
        Comment comment = dao.getById(id).orElse(null);
        if (comment == null) {
            return 0;
        }
        comment.setComment(text);
        return dao.update(comment);
    }
}
