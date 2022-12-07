package ru.otus.example.springmvcview.springmvcview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmvcview.springmvcview.domain.Author;
import ru.otus.example.springmvcview.springmvcview.dto.AuthorDto;
import ru.otus.example.springmvcview.springmvcview.service.AuthorService;

/**
 * @author s.melekhin
 * @since 12 нояб. 2022 г.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/all")
    public String listPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "author/list";
    }

    @PostMapping("/add")
    public String add(AuthorDto authorDto) {
        authorService.save(authorDto.getFirstname(), authorDto.getLastname());
        return "redirect:/author/all";
    }

    @GetMapping("/add")
    public String addPage() {
        return "author/add";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "author/edit";
    }

    @PostMapping("/edit")
    public String update(AuthorDto authorDto) {
        authorService.update(authorDto.getId(), authorDto.getFirstname(), authorDto.getLastname());
        return "redirect:/author/all";
    }

    @GetMapping("/delete")
    public String getDeletePage(@RequestParam("id") int id, Model model){
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "author/delete";
    }

    @PostMapping("/delete")
    public String delete(AuthorDto authorDto){
        authorService.deleteById(authorDto.getId());
        return "redirect:/author/all";
    }

}
