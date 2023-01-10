package ru.otus.springwebspa.springwebspa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import ru.otus.springwebspa.springwebspa.domain.Book;
import ru.otus.springwebspa.springwebspa.domain.Comment;
import ru.otus.springwebspa.springwebspa.dto.CommentDto;
import ru.otus.springwebspa.springwebspa.mapper.CommentMapper;
import ru.otus.springwebspa.springwebspa.service.CommentService;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @GetMapping("/comment/all/{bookId}")
    public List<CommentDto> getAll(@PathVariable long bookId) {
        return commentMapper.toDto(commentService.findAllByBook(bookId));
    }

    @GetMapping("/comment/{id}")
    public CommentDto getById(@PathVariable long id) {
        return commentMapper.toDto(commentService.findById(id));
    }

    @PostMapping("/comment")
    public long save(@RequestBody CommentDto commentDto) {

        Comment comment = commentMapper.toDomain(commentDto);
        comment.setBook(new Book());
        return commentService.save(comment.getComment(), comment.getId());
    }

    @PutMapping("/comment")
    public long update(@RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.toDomain(commentDto);
        return commentService.update(comment.getId(), comment.getComment());
    }

    @DeleteMapping("/comment/{id}")
    public void delete(@PathVariable long id) {
        commentService.deleteById(id);
    }

}
