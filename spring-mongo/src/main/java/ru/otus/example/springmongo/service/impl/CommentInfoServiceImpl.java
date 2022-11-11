package ru.otus.example.springmongo.service.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmongo.domain.Comment;
import ru.otus.example.springmongo.service.CommentService;
import ru.otus.example.springmongo.service.InnerInfoService;
import ru.otus.example.springmongo.utils.IOService;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
@Service("commentInfoService")
@RequiredArgsConstructor
public class CommentInfoServiceImpl implements InnerInfoService {

    private final IOService ioService;
    private final CommentService commentService;

    @Override
    public void getAllInfoByExternalEntity(String bookId) {
        List<Comment> comments = commentService.findAllByBook(bookId);
        if (comments.isEmpty()) {
            ioService.out("Книги не существует или комментариев нет");
        } else {
            ioService.out(String.format("Список комментариев к книге с id %s:", bookId));
            comments.forEach(comment -> ioService.out(comment.toString()));
        }
    }

    @Override
    public void getInfoById(String id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            ioService.out(String.format("Комментарий с id = %s не найден", id));
        } else {
            ioService.out(comment.toString());
        }
    }

    @Override
    public void save(String bookId) {
        ioService.out("Введите комментарий:");
        String text = ioService.in();
        commentService.save(text, bookId);
    }

    @Override
    public void deleteById(String id) {
        try {
            commentService.deleteById(id);
            ioService.out("Комментарий удален");
        } catch (
                EmptyResultDataAccessException e) {
            ioService.out(String.format("Комментарий с id = %s не найден", id));
        }
    }

    @Override
    public void update(String id) {
        ioService.out("Введите комментарий");
        String text = ioService.in();
        commentService.update(id, text);
    }

}
