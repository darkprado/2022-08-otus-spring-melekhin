package ru.otus.example.springorm;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringOrmApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringOrmApplication.class, args);
        Console.main(args);
    }

}
