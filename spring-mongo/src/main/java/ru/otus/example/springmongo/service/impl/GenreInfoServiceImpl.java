package ru.otus.example.springmongo.service.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmongo.domain.Genre;
import ru.otus.example.springmongo.service.GenreService;
import ru.otus.example.springmongo.service.InfoService;
import ru.otus.example.springmongo.utils.IOService;

/**
 * @author s.melekhin
 * @since 19 окт. 2022 г.
 */
@Service("genreInfoService")
@RequiredArgsConstructor
public class GenreInfoServiceImpl implements InfoService {

    private final IOService ioService;
    private final GenreService genreService;

    @Override
    public void getAllInfo() {
        List<Genre> genres = genreService.findAll();
        ioService.out("Список жанров:");
        genres.forEach(genre -> ioService.out(genre.toString()));
    }

    @Override
    public void getInfoById(String id) {
        Genre genre = genreService.findById(id);
        if (genre == null) {
            ioService.out(String.format("Жанр с id = %s не найден", id));
            getAllInfo();
        } else {
            ioService.out(genre.toString());
        }
    }

    @Override
    public void save() {
        ioService.out("Введите название жанра");
        String name = ioService.in();
        genreService.save(name);
    }

    @Override
    public void deleteById(String id) {
        try {
            genreService.deleteById(id);
            ioService.out("Жанр удален");
        } catch (EmptyResultDataAccessException e) {
            ioService.out(String.format("Жанр с id = %s не найден", id));
        }
    }

    @Override
    public void update(String id) {
        ioService.out("Введите название жанра");
        String name = ioService.in();
        genreService.update(id, name);
    }
}
