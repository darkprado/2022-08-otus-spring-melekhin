package ru.otus.example.springorm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springorm.dao.GenreDao;
import ru.otus.example.springorm.domain.Genre;
import ru.otus.example.springorm.service.GenreService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return dao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(long id) {
        return dao.getById(id).orElse(null);
    }

    @Override
    @Transactional
    public long save(String name) {
        return dao.insert(new Genre(0, name));
    }

    @Override
    @Transactional
    public long deleteById(long id) {
        return dao.deleteById(id);
    }

    @Override
    @Transactional
    public long update(long id, String name) {
        return dao.update(new Genre(id, name));
    }
}
