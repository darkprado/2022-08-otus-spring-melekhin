package ru.otus.example.springmvcview.springmvcview.dto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import ru.otus.example.springmvcview.springmvcview.domain.Book;

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
