package ru.otus.example.springmvcview.springmvcview.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmvcview.springmvcview.dao.CommentRepository;
import ru.otus.example.springmvcview.springmvcview.domain.Comment;
import ru.otus.example.springmvcview.springmvcview.service.BookService;
import ru.otus.example.springmvcview.springmvcview.service.CommentService;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;
    private final BookService bookService;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAllByBook(long bookId) {
        // коммент оставил для примера себе на память
//        return repository.findAll(Example.of(new Comment(null, null, new Book(bookId, null, null, null))));
        return repository.findByBookId(bookId);
    }

    @Override
    @Transactional(readOnly = true)
    public Comment findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public long save(String text, long bookId) {
        return repository.save(new Comment(null, text, bookService.findById(bookId))).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public long update(long id, String text) {
        Comment comment = repository.findById(id).orElse(null);
        if (comment == null) {
            return 0;
        }
        comment.setComment(text);
        return repository.save(comment).getId();
    }
}
