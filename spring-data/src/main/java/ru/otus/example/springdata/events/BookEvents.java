package ru.otus.example.springdata.events;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.example.springdata.service.InfoService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@ShellComponent
public class BookEvents {

    private final InfoService service;

    public BookEvents(@Qualifier("bookInfoService") InfoService service) {
        this.service = service;
    }

    @ShellMethod(value = "Получение всех книг", key = { "all books", "all b", "a b", "b" })
    public void getAll() {
        service.getAllInfo();
    }

    @ShellMethod(value = "Сохранение книги", key = { "save book", "save b", "s b" })
    public void saveBook() {
        service.save();
    }

    @ShellMethod(value = "Получение книги", key = { "get book", "get b", "g b" })
    public void getBook(@ShellOption long id) {
        service.getInfoById(id);
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
