package ru.otus.springwebspa.springwebspa.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ru.otus.springwebspa.springwebspa.dao.AuthorRepository;
import ru.otus.springwebspa.springwebspa.domain.Author;
import ru.otus.springwebspa.springwebspa.service.AuthorService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public long save(String firstname, String lastname) {
        return repository.save(new Author(0, firstname, lastname)).getId();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
       repository.deleteById(id);
    }

    @Override
    @Transactional
    public long update(long id, String firstname, String lastname) {
        return repository.save(new Author(id, firstname, lastname)).getId();
    }

}
