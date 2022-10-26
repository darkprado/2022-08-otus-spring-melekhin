package ru.otus.example.springdata.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springdata.dao.GenreRepository;
import ru.otus.example.springdata.domain.Genre;
import ru.otus.example.springdata.service.GenreService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genre findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public long save(String name) {
        return repository.save(new Genre(0, name)).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public long update(long id, String name) {
        return repository.save(new Genre(id, name)).getId();
    }
}
