package ru.otus.example.springmvcview.springmvcview.dto;

import lombok.Data;

/**
 * @author s.melekhin
 * @since 22 нояб. 2022 г.
 */
@Data
public class CommentDto {

    private Long id;
    private String comment;
    private long bookId;

}
