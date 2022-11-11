package ru.otus.example.springmongo.mongock.changelog;

import java.util.Arrays;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

import ru.otus.example.springmongo.dao.AuthorRepository;
import ru.otus.example.springmongo.dao.BookRepository;
import ru.otus.example.springmongo.dao.CommentRepository;
import ru.otus.example.springmongo.dao.GenreRepository;
import ru.otus.example.springmongo.domain.Author;
import ru.otus.example.springmongo.domain.Book;
import ru.otus.example.springmongo.domain.Comment;
import ru.otus.example.springmongo.domain.Genre;

@ChangeLog
public class DatabaseChangelog {

    private Author author1;
    private Author author2;
    private Author author3;

    private Genre genre1;
    private Genre genre2;
    private Genre genre3;

    private Book book1;
    private Book book2;
    private Book book3;

    @ChangeSet(order = "001", id = "dropDb", author = "darkprado", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertAuthors", author = "darkprado")
    public void insertAuthors(AuthorRepository repository) {
        author1 = repository.save(new Author("1", "Лев", "Толстой"));
        author2 = repository.save(new Author("2", "Ю", "Несбё"));
        author3 = repository.save(new Author("3", "Уильям", "Шекспир"));
    }

    @ChangeSet(order = "003", id = "insertGenres", author = "darkprado")
    public void insertGenres(GenreRepository repository) {
        genre1 = repository.save(new Genre("1", "Роман"));
        genre2 = repository.save(new Genre("2", "Трагедия"));
        genre3 = repository.save(new Genre("3", "Детектив"));
    }

    @ChangeSet(order = "004", id = "insertBooks", author = "darkprado")
    public void insertBooks(BookRepository repository) {
        book1 = repository.save(new Book("1", "Война и мир", author1, genre1));
        book2 = repository.save(new Book("2", "Снеговик", author2, genre3));
        book3 = repository.save(new Book("3", "Король лир", author3, genre2));
    }

    @ChangeSet(order = "005", id = "insertComments", author = "darkprado")
    public void insertComments(CommentRepository repository) {
        Arrays.asList(new String[]{"comment for book", "good book", "bad book", "readable book"}).forEach(
                comment -> repository.save(new Comment(comment, book1))
        );
        Arrays.asList(new String[]{"comment1", "good"}).forEach(
                comment -> repository.save(new Comment(comment, book2))
        );
        repository.save(new Comment("!!!!!!!!!!!!!!!!", book3));
    }

}
