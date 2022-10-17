package ru.otus.example.springjdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.dao.AuthorDao;
import ru.otus.example.springjdbc.domain.Author;
import ru.otus.example.springjdbc.service.AuthorService;
import ru.otus.example.springjdbc.utils.IOService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;
    private final IOService ioService;

    @Override
    public void findAll() {
        List<Author> authors = dao.getAll();
        ioService.out("Список авторов:");
        authors.forEach(author -> ioService.out(author.toString()));
    }

    @Override
    public void findById(long id) {
        Author author = dao.getById(id).orElse(null);
        if (author == null) {
            ioService.out(String.format("Автор с id = %s не найден", id));
            findAll();
        } else {
            ioService.out(author.toString());
        }
    }

    @Override
    public void save() {
        ioService.out("Введите имя автора");
        String firstname = ioService.in();
        ioService.out("Введите фамилию автора");
        String lastname = ioService.in();
        dao.insert(new Author(firstname, lastname));
    }

    @Override
    public void deleteById(long id) {
        if (dao.deleteById(id) == 1) {
            ioService.out("Автор удален");
        } else {
            ioService.out(String.format("Автор c id = %s не найден", id));
        }
    }

    @Override
    public void update(long id) {
        ioService.out("Введите имя автора");
        String firstname = ioService.in();
        ioService.out("Введите фамилию автора");
        String lastname = ioService.in();
        dao.update(new Author(id, firstname, lastname));
    }

}
