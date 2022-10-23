package ru.otus.example.springjdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.dao.AuthorDao;
import ru.otus.example.springjdbc.domain.Author;
import ru.otus.example.springjdbc.service.AuthorService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    public List<Author> findAll() {
        return dao.getAll();
    }

    @Override
    public Author findById(long id) {
        return dao.getById(id).orElse(null);
    }

    @Override
    public long save(String firstname, String lastname) {
        return dao.insert(new Author(firstname, lastname));
    }

    @Override
    public long deleteById(long id) {
       return dao.deleteById(id);
    }

    @Override
    public long update(long id, String firstname, String lastname) {
        return dao.update(new Author(id, firstname, lastname));
    }

}
