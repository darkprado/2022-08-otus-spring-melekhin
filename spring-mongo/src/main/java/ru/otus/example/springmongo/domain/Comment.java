package ru.otus.example.springmongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
public class Comment {

    @Id
    private String id;

    private String comment;

    @DBRef
    private Book book;

    public Comment(String comment, Book book) {
        this.comment = comment;
        this.book = book;
    }
}
