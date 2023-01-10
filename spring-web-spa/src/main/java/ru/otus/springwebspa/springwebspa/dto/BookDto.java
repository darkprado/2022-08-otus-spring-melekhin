package ru.otus.springwebspa.springwebspa.dto;

import lombok.Data;

/**
 * @author s.melekhin
 * @since 05 дек. 2022 г.
 */
@Data
public class BookDto {

    private long id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;

}
