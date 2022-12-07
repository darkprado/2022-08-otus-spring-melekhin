package ru.otus.example.springmvcview.springmvcview.dto;

import lombok.Data;

/**
 * @author s.melekhin
 * @since 22 нояб. 2022 г.
 */
@Data
public class BookDto {

    private long id;
    private String name;
    private long author;
    private long genre;

}
