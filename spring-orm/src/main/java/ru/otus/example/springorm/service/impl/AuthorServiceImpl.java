package ru.otus.example.springorm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springorm.dao.AuthorDao;
import ru.otus.example.springorm.domain.Author;
import ru.otus.example.springorm.service.AuthorService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return dao.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(long id) {
        return dao.getById(id).orElse(null);
    }

    @Override
    @Transactional
    public long save(String firstname, String lastname) {
        return dao.insert(new Author(0, firstname, lastname));
    }

    @Override
    @Transactional
    public long deleteById(long id) {
       return dao.deleteById(id);
    }

    @Override
    @Transactional
    public long update(long id, String firstname, String lastname) {
        return dao.update(new Author(id, firstname, lastname));
    }

}
