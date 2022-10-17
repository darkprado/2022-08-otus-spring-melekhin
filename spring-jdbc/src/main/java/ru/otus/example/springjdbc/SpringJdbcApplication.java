package ru.otus.example.springjdbc;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import ru.otus.example.springjdbc.dao.AuthorDao;
import ru.otus.example.springjdbc.dao.BookDao;
import ru.otus.example.springjdbc.dao.GenreDao;

@RequiredArgsConstructor
@SpringBootApplication
public class SpringJdbcApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringJdbcApplication.class, args);
        Console.main(args);
    }

}
