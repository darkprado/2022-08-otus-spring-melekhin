package ru.otus.example.springmvcview.springmvcview.domain;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author s.melekhin
 * @since 12 ноя. 2022 г.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_text")
    private String comment;
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Override
    public String toString() {
        return String.format("Комментарий с id = %s: %s", id, comment);
    }

}
