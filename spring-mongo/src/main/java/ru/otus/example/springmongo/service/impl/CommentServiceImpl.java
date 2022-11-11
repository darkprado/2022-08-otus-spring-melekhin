package ru.otus.example.springmongo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmongo.dao.CommentRepository;
import ru.otus.example.springmongo.domain.Comment;
import ru.otus.example.springmongo.service.BookService;
import ru.otus.example.springmongo.service.CommentService;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAllByBook(String bookId) {
        return repository.findAllByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public String save(String text, String bookId) {
        return repository.save(new Comment(text, bookService.findById(bookId))).getId();
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public String update(String id, String text) {
        Comment comment = findById(id);
        if (comment == null) {
            return null;
        }
        comment.setComment(text);
        return repository.save(comment).getId();
    }
}
