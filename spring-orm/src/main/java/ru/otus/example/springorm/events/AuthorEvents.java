package ru.otus.example.springorm.events;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.example.springorm.service.InfoService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@ShellComponent
public class AuthorEvents {

    private final InfoService service;

    public AuthorEvents(@Qualifier("authorInfoService") InfoService service) {
        this.service = service;
    }

    @ShellMethod(value = "Получение всех авторов", key = { "all authors", "all a", "a a", "a" })
    public void getAll() {
        service.getAllInfo();
    }

    @ShellMethod(value = "Сохранение автора", key = { "save author", "save a", "s a" })
    public void saveAuthor() {
        service.save();
    }

    @ShellMethod(value = "Получение автора", key = { "get author", "get a", "g a" })
    public void getAuthor(@ShellOption long id) {
        service.getInfoById(id);
    }

    @ShellMethod(value = "Обновление автора", key = { "update author", "update a", "u a" })
    public void updateAuthor(@ShellOption long id) {
        service.update(id);
    }

    @ShellMethod(value = "Удаление автора", key = { "delete author", "delete a", "d a" })
    public void deleteAuthor(@ShellOption long id) {
        service.deleteById(id);
    }

}
