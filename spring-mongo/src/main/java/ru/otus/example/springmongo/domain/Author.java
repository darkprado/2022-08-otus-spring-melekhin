package ru.otus.example.springmongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author s.melekhin
 * @since 06 нояб. 2022 г.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Author {

    @Id
    private String id;

    private String firstname;

    private String lastname;

    public Author(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

}
