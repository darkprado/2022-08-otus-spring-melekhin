package ru.otus.example.springdata.service.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springdata.domain.Comment;
import ru.otus.example.springdata.service.CommentService;
import ru.otus.example.springdata.service.InnerInfoService;
import ru.otus.example.springdata.utils.IOService;

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
    public void getAllInfoByExternalEntity(long bookId) {
        List<Comment> comments = commentService.findAllByBook(bookId);
        if (comments.isEmpty()) {
            ioService.out("Книги не существует или комментариев нет");
        } else {
            ioService.out(String.format("Список комментариев к книге с id %s:", bookId));
            comments.forEach(comment -> ioService.out(comment.toString()));
        }
    }

    @Override
    public void getInfoById(long id) {
        Comment comment = commentService.findById(id);
        if (comment == null) {
            ioService.out(String.format("Комментарий с id = %s не найден", id));
        } else {
            ioService.out(comment.toString());
        }
    }

    @Override
    public void save(long bookId) {
        ioService.out("Введите комментарий:");
        String text = ioService.in();
        commentService.save(text, bookId);
    }

    @Override
    public void deleteById(long id) {
        try {
            commentService.deleteById(id);
            ioService.out("Комментарий удален");
        } catch (
                EmptyResultDataAccessException e) {
            ioService.out(String.format("Комментарий с id = %s не найден", id));
        }
    }

    @Override
    public void update(long id) {
        ioService.out("Введите комментарий");
        String text = ioService.in();
        commentService.update(id, text);
    }

}
