package ru.otus.example.springjdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.dao.GenreDao;
import ru.otus.example.springjdbc.domain.Genre;
import ru.otus.example.springjdbc.service.GenreService;
import ru.otus.example.springjdbc.utils.IOService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;
    private final IOService ioService;

    @Override
    public void findAll() {
        List<Genre> genres = dao.getAll();
        ioService.out("Список жанров:");
        genres.forEach(genre -> ioService.out(genre.toString()));
    }

    @Override
    public void findById(long id) {
        Genre genre = dao.getById(id).orElse(null);
        if (genre == null) {
            ioService.out(String.format("Жанр с id = %s не найден", id));
            findAll();
        } else {
            ioService.out(genre.toString());
        }
    }

    @Override
    public void save() {
        ioService.out("Введите название жанра");
        String name = ioService.in();
        dao.insert(new Genre(name));
    }

    @Override
    public void deleteById(long id) {
        if (dao.deleteById(id) == 1) {
            ioService.out("Жанр удален");
        } else {
            ioService.out(String.format("Жанр c id = %s не найден", id));
        }
    }

    @Override
    public void update(long id) {
        ioService.out("Введите название жанра");
        String name = ioService.in();
        dao.update(new Genre(id, name));
    }
}
