package ru.otus.example.springmongo.service.impl;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springmongo.domain.Author;
import ru.otus.example.springmongo.service.AuthorService;
import ru.otus.example.springmongo.service.InfoService;
import ru.otus.example.springmongo.utils.IOService;

/**
 * @author s.melekhin
 * @since 19 окт. 2022 г.
 */
@Service("authorInfoService")
@RequiredArgsConstructor
public class AuthorInfoServiceImpl implements InfoService {

    private final IOService ioService;
    private final AuthorService authorService;

    @Override
    public void getAllInfo() {
        List<Author> authors = authorService.findAll();
        ioService.out("Список авторов:");
        authors.forEach(author -> ioService.out(author.toString()));
    }

    @Override
    public void getInfoById(String id) {
        Author author = authorService.findById(id);
        if (author == null) {
            ioService.out(String.format("Автор с id = %s не найден", id));
            getAllInfo();
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
        authorService.save(firstname, lastname);
    }

    @Override
    public void deleteById(String id) {
        try {
            authorService.deleteById(id);
            ioService.out("Автор удален");
        } catch (EmptyResultDataAccessException e) {
            ioService.out(String.format("Автор с id = %s не найден", id));
        }
    }

    @Override
    public void update(String id) {
        ioService.out("Введите имя автора");
        String firstname = ioService.in();
        ioService.out("Введите фамилию автора");
        String lastname = ioService.in();
        authorService.update(id, firstname, lastname);
    }
}
