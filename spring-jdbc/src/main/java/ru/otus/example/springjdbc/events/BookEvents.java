package ru.otus.example.springjdbc.events;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.service.BookService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@ShellComponent
@RequiredArgsConstructor
public class BookEvents {

    private final BookService service;

    @ShellMethod(value = "Получение всех книг", key = { "all books", "all b", "a b", "b" })
    public void getAll() {
        service.findAll();
    }

    @ShellMethod(value = "Сохранение книги", key = { "save book", "save b", "s b" })
    public void saveBook() {
        service.save();
    }

    @ShellMethod(value = "Получение книги", key = { "get book", "get b", "g b" })
    public void getBook(@ShellOption long id) {
        service.findById(id);
    }

    @ShellMethod(value = "Обновление книги", key = { "update book", "update b", "u b" })
    public void updateBook(@ShellOption long id) {
        service.update(id);
    }

    @ShellMethod(value = "Удаление книги", key = { "delete book", "delete b", "d b" })
    public void deleteBook(@ShellOption long id) {
        service.deleteById(id);
    }
}
