package ru.otus.springwebspa.springwebspa.dto;

import lombok.Data;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@Data
public class CommentDto {

    private Long id;
    private String comment;
    private BookDto book;

    @Override
    public String toString() {
        return String.format("Комментарий с id = %s: %s", id, comment);
    }

}
