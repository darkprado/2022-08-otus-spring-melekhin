package ru.otus.example.springmvcview.springmvcview;

import java.sql.SQLException;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringmvcviewApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(SpringmvcviewApplication.class, args);
//        Console.main(args);
    }

}
