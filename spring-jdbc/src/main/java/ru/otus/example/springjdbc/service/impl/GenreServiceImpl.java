package ru.otus.example.springjdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.dao.GenreDao;
import ru.otus.example.springjdbc.domain.Genre;
import ru.otus.example.springjdbc.service.GenreService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Override
    public List<Genre> findAll() {
        return dao.getAll();
    }

    @Override
    public Genre findById(long id) {
        return dao.getById(id).orElse(null);
    }

    @Override
    public long save(String name) {
        return dao.insert(new Genre(name));
    }

    @Override
    public long deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    public long update(long id, String name) {
        return dao.update(new Genre(id, name));
    }
}
