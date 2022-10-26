package ru.otus.example.springdata.events;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import ru.otus.example.springdata.service.InnerInfoService;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
@ShellComponent
public class CommentEvents {

    private final InnerInfoService service;

    public CommentEvents(InnerInfoService service) {
        this.service = service;
    }

    @ShellMethod(value = "Получение всех комментариев к книге", key = { "all comments", "all c", "c" })
    public void getAllByBook(@ShellOption long bookId) {
        service.getAllInfoByExternalEntity(bookId);
    }

    @ShellMethod(value = "Сохранение комментария", key = { "save comment", "save c", "s c" })
    public void saveComment(@ShellOption long bookId) {
        service.save(bookId);
    }

    @ShellMethod(value = "Получение комментария", key = { "get comment", "get c", "g c" })
    public void getComment(@ShellOption long id) {
        service.getInfoById(id);
    }

    @ShellMethod(value = "Обновление комментария", key = { "update comment", "update c", "u c" })
    public void updateComment(@ShellOption long id) {
        service.update(id);
    }

    @ShellMethod(value = "Удаление комментария", key = { "delete comment", "delete c", "d c" })
    public void deleteComment(@ShellOption long id) {
        service.deleteById(id);
    }

}
