package ru.otus.example.springmvcview.springmvcview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmvcview.springmvcview.domain.Comment;
import ru.otus.example.springmvcview.springmvcview.dto.CommentDto;
import ru.otus.example.springmvcview.springmvcview.service.CommentService;

/**
 * @author s.melekhin
 * @since 12 нояб. 2022 г.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/all/book")
    public String listPage(@RequestParam("id") int id, Model model) {
        List<Comment> comments = commentService.findAllByBook(id);
        if (comments.isEmpty()) {
            model.addAttribute("bookId", id);
            return "comment/add";
        }
        model.addAttribute("comments", comments);
        return "comment/list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Comment comment = commentService.findById(id);
        model.addAttribute("comment", comment);
        return "comment/edit";
    }

    @PostMapping("/edit")
    public String update(CommentDto commentDto) {
        commentService.update(commentDto.getId(), commentDto.getComment());
        return "redirect:/book/all";
    }

    @PostMapping("/add")
    public String add(CommentDto commentDto) {
        commentService.save(commentDto.getComment(), commentDto.getBookId());
        return "redirect:/book/all";
    }

    @GetMapping("/add")
    public String addPage(@RequestParam("bookId") int bookId, Model model) {
        model.addAttribute("bookId", bookId);
        return "comment/add";
    }

    @GetMapping("/delete")
    public String getDeletePage(@RequestParam("id") int id, Model model){
        Comment comment = commentService.findById(id);
        model.addAttribute("comment", comment);
        return "comment/delete";
    }

    @PostMapping("/delete")
    public String delete(CommentDto commentDto){
        commentService.deleteById(commentDto.getId());
        return "redirect:/book/all";
    }

}
