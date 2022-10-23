package ru.otus.example.springjdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author s.melekhin
 * @since 10 окт. 2022 г.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Author {

    private long id;
    private final String firstname;
    private final String lastname;

}
