package ru.otus.example.springorm.domain;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author s.melekhin
 * @since 24 окт. 2022 г.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "comments")
@NamedEntityGraph(name = "comment-graph",
        attributeNodes = { @NamedAttributeNode(value = "book", subgraph = "book-for-comment-graph") }
        , subgraphs = {
        @NamedSubgraph(name = "book-for-comment-graph",
                attributeNodes = {
                        @NamedAttributeNode(value = "id"),
                        @NamedAttributeNode(value = "name")
                })
})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "comment_text")
    private String comment;
    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "book_id")
    private Book book;

    @Override
    public String toString() {
        return String.format("Комментарий с id = %s: %s", id, comment);
    }

}
