package ru.otus.example.springmvcview.springmvcview.dto;

import javax.persistence.Column;

import lombok.Data;

/**
 * @author s.melekhin
 * @since 22 нояб. 2022 г.
 */
@Data
public class AuthorDto {

    private long id;
    private String firstname;
    private String lastname;

}
