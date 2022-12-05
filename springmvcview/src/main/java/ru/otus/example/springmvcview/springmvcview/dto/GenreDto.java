package ru.otus.example.springmvcview.springmvcview.dto;

import javax.persistence.Column;

import lombok.Data;

/**
 * @author s.melekhin
 * @since 22 нояб. 2022 г.
 */
@Data
public class GenreDto {

    private long id;
    private String name;

}
