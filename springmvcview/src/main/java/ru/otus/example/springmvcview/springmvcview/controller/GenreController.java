package ru.otus.example.springmvcview.springmvcview.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmvcview.springmvcview.domain.Genre;
import ru.otus.example.springmvcview.springmvcview.dto.GenreDto;
import ru.otus.example.springmvcview.springmvcview.service.GenreService;

/**
 * @author s.melekhin
 * @since 12 нояб. 2022 г.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/all")
    public String listPage(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
        return "genre/list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        return "genre/edit";
    }

    @PostMapping("/edit")
    public String update(GenreDto genreDto) {
        genreService.update(genreDto.getId(), genreDto.getName());
        return "redirect:/genre/all";
    }

    @PostMapping("/add")
    public String add(GenreDto genreDto) {
        genreService.save(genreDto.getName());
        return "redirect:/genre/all";
    }

    @GetMapping("/add")
    public String addPage() {
        return "genre/add";
    }

    @GetMapping("/delete")
    public String getDeletePage(@RequestParam("id") int id, Model model){
        Genre genre = genreService.findById(id);
        model.addAttribute("genre", genre);
        return "genre/delete";
    }

    @PostMapping("/delete")
    public String delete(GenreDto genreDto){
        genreService.deleteById(genreDto.getId());
        return "redirect:/genre/all";
    }

}
