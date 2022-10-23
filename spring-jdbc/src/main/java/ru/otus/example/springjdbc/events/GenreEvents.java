package ru.otus.example.springjdbc.events;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.example.springjdbc.service.InfoService;

/**
 * @author s.melekhin
 * @since 16 окт. 2022 г.
 */
@ShellComponent
public class GenreEvents {

    private final InfoService service;

    public GenreEvents(@Qualifier("genreInfoService") InfoService service) {
        this.service = service;
    }

    @ShellMethod(value = "Получение всех жанров", key = { "all genres", "all g", "g" })
    public void getAll() {
        service.getAllInfo();
    }

    @ShellMethod(value = "Сохранение жанра", key = { "save genre", "save g", "s g" })
    public void saveGenre() {
        service.save();
    }

    @ShellMethod(value = "Получение жанра", key = { "get genre", "get g", "g g" })
    public void getGenre(@ShellOption long id) {
        service.getInfoById(id);
    }

    @ShellMethod(value = "Обновление жанра", key = { "update genre", "update g", "u g" })
    public void updateGenre(@ShellOption long id) {
        service.update(id);
    }

    @ShellMethod(value = "Удаление жанра", key = { "delete genre", "delete g", "d g" })
    public void deleteGenre(@ShellOption long id) {
        service.deleteById(id);
    }

}
