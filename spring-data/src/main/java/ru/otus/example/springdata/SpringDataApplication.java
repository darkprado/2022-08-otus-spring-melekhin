package ru.otus.example.springdata;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDataApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringDataApplication.class, args);
        Console.main(args);
    }

}
